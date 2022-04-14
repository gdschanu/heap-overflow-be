package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.coreProblem.services.testCase.SearchTestCaseService;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final RunCodeService runCodeService;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEventRepository submissionEventRepository;
    private final SearchTestCaseService searchTestCaseService;

    @Override
    public Output submit(Input input) {
        Output output = runTests(input);
        Submission submission = Submission.create(
                input.problemId,
                input.programmingLanguage,
                output.runTime,
                output.memory,
                input.code,
                output.status,
                output.failedTestCaseDetail == null ? null :
                        hanu.gdsc.coreProblem.domains.FailedTestCaseDetail.create(
                                output.failedTestCaseDetail.failedAtLine,
                                output.failedTestCaseDetail.input,
                                output.failedTestCaseDetail.actualOutput,
                                output.failedTestCaseDetail.expectedOutput,
                                output.failedTestCaseDetail.description
                        ),
                input.serviceToCreate,
                input.coderId
        );
        submissionRepository.create(submission);
        SubmissionEvent submissionEvent = SubmissionEvent.create(
                input.problemId,
                output.status
        );
        submissionEventRepository.create(submissionEvent);
        return output;
    }

    private Output runTests(Input input) {
        Problem problem = problemRepository.getById(input.problemId, input.serviceToCreate);
        if (!problem.getAllowedProgrammingLanguages().contains(input.programmingLanguage)) {
            throw new BusinessLogicError("Bài tập không hỗ trợ ngôn ngữ lập trình " + input.programmingLanguage, "LANGUAGE_NOT_SUPPORTED");
        }
        int testCaseCount = 0;
        float totalMemory = 0;
        long totalRunTime = 0;
        List<TestCase> testCases = searchTestCaseService.getByProblemId(problem.getId(), problem.getServiceToCreate());
        for (TestCase testCase : TestCase.getSortedByOrdinalTestCases(testCases)) {
            RunCodeService.Output runCodeServiceOutput = runCodeService.execute(input.code, testCase.getInput(), input.programmingLanguage);
            //Check compilation status
            if (runCodeServiceOutput.compilationError == true) {
                return Output.builder()
                        .runTime(null)
                        .memory(null)
                        .status(Status.CE)
                        .failedTestCaseDetail(null)
                        .compilationMessage(runCodeServiceOutput.compilationMessage)
                        .build();
            }
            // check std status
            if (runCodeServiceOutput.stdError == true) {
                return Output.builder()
                        .runTime(null)
                        .memory(null)
                        .status(Status.STDE)
                        .failedTestCaseDetail(null)
                        .compilationMessage(null)
                        .stdMessage(runCodeServiceOutput.stdMessage)
                        .build();
            }
            // Check time limit
            TimeLimit timeLimit = problem.getTimeLimitByProgrammingLanguage(input.programmingLanguage);
            if (runCodeServiceOutput.runTime.greaterThan(timeLimit.getTimeLimit())) {
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.TLE)
                        .failedTestCaseDetail(null)
                        .compilationMessage(null)
                        .stdMessage(null)
                        .build();
            }
            // Check memory limit
            MemoryLimit memoryLimit = problem.getMemoryLimitByProgrammingLanguage(input.programmingLanguage);
            if (runCodeServiceOutput.memory.greaterThan(memoryLimit.getMemoryLimit())) {
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.MLE)
                        .failedTestCaseDetail(null)
                        .compilationMessage(null)
                        .stdMessage(null)
                        .build();
            }
            // Check answer
            if (!runCodeServiceOutput.output.equals(testCase.getExpectedOutput())) {
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.WA)
                        .failedTestCaseDetail(
                                FailedTestCaseDetail.builder()
                                        .failedAtLine(runCodeServiceOutput.output.getFailedAtLine())
                                        .input(testCase.getInput())
                                        .actualOutput(runCodeServiceOutput.output.toString())
                                        .expectedOutput(testCase.getExpectedOutput())
                                        .description(testCase.getDescription())
                                        .build()
                        )
                        .compilationMessage(null)
                        .stdMessage(null)
                        .build();
            }
            totalMemory += runCodeServiceOutput.memory.getValue();
            totalRunTime += runCodeServiceOutput.runTime.getValue();
            testCaseCount++;
        }
        return Output.builder()
                .memory(new KB((float) totalMemory / testCaseCount))
                .runTime(new Millisecond((long) totalRunTime / testCaseCount))
                .status(Status.AC)
                .failedTestCaseDetail(null)
                .compilationMessage(null)
                .stdMessage(null)
                .build();
    }
}

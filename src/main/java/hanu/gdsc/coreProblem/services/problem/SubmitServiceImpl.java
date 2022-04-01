package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.EventRepository;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final RunCodeService runCodeService;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEventRepository submissionEventRepository;

    @Override
    public Output submit(Input input) {
        Output output = runTests(input);
        FailedTestCaseDetail failedTestCaseDetail = output.failedTestCase == null ? null : 
            FailedTestCaseDetail.fromTestCase(
            failedAtLine,
            output.actualOutput,
            output.failedTestCase
            );
        Submission submission = Submission.create(
                input.problemId,
                input.programmingLanguage,
                output.runTime,
                output.memory,
                input.code,
                output.status,
                failedTestCaseDetail,
                input.serviceName
        );
        submissionRepository.create(submission);
        SubmissionEvent submissionEvent = SubmissionEvent.create(input.problemId, output.status);
        return output;
    }

    private Output runTests(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (!problem.getAllowedProgrammingLanguages().contains(input.programmingLanguage)) {
            throw new BusinessLogicError("Bài tập không hỗ trợ ngôn ngữ lập trình " + input.programmingLanguage, "LANGUAGE_NOT_SUPPORTED");
        }
        int testCaseCount = 0;
        float totalMemory = 0;
        long totalRunTime = 0;
        for (TestCase testCase : problem.getSortedByOrdinalTestCases()) {
            RunCodeService.Output runCodeServiceOutput = runCodeService.execute(input.code, testCase.getInput(), input.programmingLanguage);
            //Check compilation status
            if (runCodeServiceOutput.compilationError == true) {
                return Output.builder()
                        .runTime(null)
                        .memory(null)
                        .status(Status.CE)
                        .failedTestCase(null)
                        .actualOutput(null)
                        .compilationMessage(runCodeServiceOutput.compilationMessage)
                        .stdMessage(null)
                        .build();
            }
            // check std status
            if (runCodeServiceOutput.stdError == true) {
                return Output.builder()
                        .runTime(null)
                        .memory(null)
                        .status(Status.STDE)
                        .failedTestCase(null)
                        .actualOutput(null)
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
                        .failedTestCase(null)
                        .actualOutput(null)
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
                        .failedTestCase(null)
                        .actualOutput(null)
                        .compilationMessage(null)
                        .stdMessage(null)
                        .build();
            }
            // Check answer
            if (!runCodeServiceOutput.output.equals(testCase.getExpectedOutput())) {
                failedAtLine = runCodeServiceOutput.output.calculateFailedLine(testCase.getExpectedOutput());
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.WA)
                        .failedTestCase(testCase)
                        .actualOutput(runCodeServiceOutput.output.toString())
                        .compilationMessage(null)
                        .stdMessage(null)
                        .build();
            }
            totalMemory += runCodeServiceOutput.memory.getValue();
            totalRunTime += runCodeServiceOutput.runTime.getValue();
            testCaseCount++;
        }
        return Output.builder()
                .memory(new KB((float) totalMemory / testCaseCount)) // TODO: calculate average run time & memory
                .runTime(new Millisecond((long) totalRunTime / testCaseCount))
                .status(Status.AC)
                .failedTestCase(null)
                .actualOutput(null)
                .compilationMessage(null)
                .stdMessage(null)
                .build();
    }
}

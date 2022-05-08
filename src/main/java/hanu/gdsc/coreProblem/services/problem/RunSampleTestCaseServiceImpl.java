package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class RunSampleTestCaseServiceImpl implements RunSampleTestCaseService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired 
    private TestCaseRepository testCaseRepository;
    @Autowired
    private RunCodeService runCodeService;

    @Override
    public Output runSampleTestCase(Input input) {
        Problem problem = problemRepository.getById(input.problemId, input.serviceToCreate);
        if (!problem.getAllowedProgrammingLanguages().contains(input.programmingLanguage)) {
            throw new BusinessLogicError("Problem not support this programming language" + input.programmingLanguage, "LANGUAGE_NOT_SUPPORTED");
        }
        int testCaseCount = 0;
        float totalMemory = 0;
        long totalRunTime = 0;
        List<TestCase> testCases = testCaseRepository.getSampleTestCases(problem.getId(), input.serviceToCreate);
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
            .memory(new KB(testCaseCount == 0 ? 0 : (float) totalMemory / testCaseCount))
            .runTime(new Millisecond(testCaseCount == 0 ? 0 : (long) totalRunTime / testCaseCount))
            .status(Status.AC)
            .failedTestCaseDetail(null)
            .compilationMessage(null)
            .stdMessage(null)
            .build();
    }
}
    

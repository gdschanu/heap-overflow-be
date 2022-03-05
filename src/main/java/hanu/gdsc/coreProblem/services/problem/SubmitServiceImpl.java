package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final RunCodeService runCodeService;

    @Override
    public Output submit(Input input) {
        Problem problem = problemRepository.getById(new Id(input.problemId));
        if (!problem.getAllowedProgrammingLanguages().contains(input.programmingLanguage)) {
            throw new BusinessLogicError("Bài tập không hỗ trợ ngôn ngữ lập trình " + input.programmingLanguage);
        }
        for (TestCase testCase : problem.getSortedByOrdinalTestCases()) {
            RunCodeService.Output runCodeServiceOutput = runCodeService.execute(input.code, testCase.getInput(), input.programmingLanguage);
            // Check time limit
            TimeLimit timeLimit = problem.getTimeLimitByProgrammingLanguage(input.programmingLanguage);
            if (runCodeServiceOutput.runTime.greaterThan(timeLimit.getTimeLimit())) {
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.TLE)
                        .failedTestCase(null)
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
                        .build();
            }
            // Check answer
            if (runCodeServiceOutput.output.equals(testCase.getExpectedOutput())) {
                return Output.builder()
                        .runTime(runCodeServiceOutput.runTime)
                        .memory(runCodeServiceOutput.memory)
                        .status(Status.WA)
                        .failedTestCase(testCase)
                        .build();
            }
        }
        return Output.builder()
                .memory(null) // TODO: calculate average run time & memory
                .runTime(null)
                .status(Status.AC)
                .build();
    }
}

package hanu.gdsc.coreProblem.services.problem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;

@AllArgsConstructor
@Service
public class SubmitServiceImpl implements SubmitService {
    private final ProblemRepository problemRepository;
    private final RunCodeService runCodeService;

    @Override
    public Output submit(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        Problem.SubmitOutput submitOutput = problem.submit(input.code, input.programmingLanguage, runCodeService);
        return Output.builder()
                .memory(submitOutput.memory)
                .runTime(submitOutput.runTime)
                .status(submitOutput.status)
                .failedTestCase(submitOutput.failedTestCase)
                .build();
    }
}

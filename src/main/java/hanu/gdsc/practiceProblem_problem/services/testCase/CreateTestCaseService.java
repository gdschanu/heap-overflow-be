package hanu.gdsc.practiceProblem_problem.services.testCase;

import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTestCaseService {
    private final hanu.gdsc.core_problem.services.testCase.CreateTestCaseService createCoreTestCaseService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    public void execute(Input input) {
        Problem problem = problemRepository.getById(new Id(input.problemId));
        if (problem == null)
            throw new NotFoundError("Unknown problem");
        createCoreTestCaseService.create(new hanu.gdsc.core_problem.services.testCase.CreateTestCaseService.Input(
                problem.getCoreProblemProblemId(),
                input.input,
                input.expectedOutput,
                input.ordinal,
                input.isSample,
                input.description,
                ServiceName.serviceName
        ));
    }
}

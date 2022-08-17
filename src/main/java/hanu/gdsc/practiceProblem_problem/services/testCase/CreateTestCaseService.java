package hanu.gdsc.practiceProblem_problem.services.testCase;

import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "Create Test Case", description = "Data transfer object for TestCase to create")
    public static class InputCreate {
        @Schema(description = "specify the id of problem", example = "627778618119e29412c16201", required = true)
        public String problemId;
        @Schema(description = "specify the input of this testcase in problem", example = "String", required = true)
        public String input;
        @Schema(description = "specify the expectedOutput of this testcase in problem", example = "String", required = true)
        public String expectedOutput;
        @Schema(description = "specify the oridinal of this testcase in problem", example = "1", required = true)
        public int ordinal;
        @Schema(description = "specify this testcase is sample or not", example = "true", required = true)
        public boolean isSample;
        @Schema(description = "specify the description of this testcase", example = "blablablabla", required = true)
        public String description;
    }

    public void execute(InputCreate input) {
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

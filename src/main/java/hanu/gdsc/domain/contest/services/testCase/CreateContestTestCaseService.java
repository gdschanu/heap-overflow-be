package hanu.gdsc.domain.contest.services.testCase;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.services.testCase.CreateTestCaseService;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class CreateContestTestCaseService {
    private final CreateTestCaseService createTestCaseService;
    private final ContestRepository contestRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "Create Test Case", description = "Data transfer object for TestCase to create")
    public static class InputCreate {
        @Schema(description = "specify the id of contest", example = "627778618119e29412c16201", required = true)
        public Id contestId;
        @Schema(description = "specify the ordinal of contest problem", example = "1", required = true)
        public int contestProblemOrdinal;
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

    public void create(InputCreate input) throws NotFoundException, InvalidInputException {
        final Contest contest = contestRepository.getById(input.contestId);
        if (contest == null)
            throw new NotFoundException("Unknown contest");
        final ContestProblem contestProblem = contest.getProblem(input.contestProblemOrdinal);
        if (contestProblem == null)
            throw new NotFoundException("Unknown contest problem");
        createTestCaseService.create(new CreateTestCaseService.Input(
                contestProblem.getCoreProblemId(),
                input.input,
                input.expectedOutput,
                input.ordinal,
                input.isSample,
                input.description,
                ContestServiceName.serviceName
        ));
    }
}

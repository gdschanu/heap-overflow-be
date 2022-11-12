package hanu.gdsc.domain.contest.services.testCase;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.services.testCase.CreateTestCaseService;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateContestTestCaseService {
    private final CreateTestCaseService createTestCaseService;
    private final ContestRepository contestRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id contestId;
        public int contestProblemOrdinal;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    public void create(Input input) throws NotFoundException, InvalidInputException {
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

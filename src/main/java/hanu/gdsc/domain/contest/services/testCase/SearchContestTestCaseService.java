package hanu.gdsc.domain.contest.services.testCase;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.core_problem.services.testCase.SearchTestCaseService;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SearchContestTestCaseService {
    private final SearchTestCaseService searchCoreTestCaseService;
    private final ContestRepository contestRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id contestId;
        public int contestProblemOrdinal;
        public boolean isSample;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public String description;
    }

    private Output toOutput(TestCase testCase, Id contestId, int contestProblemOrdinal) {
        return new Output(
                contestId,
                contestProblemOrdinal,
                testCase.isSample(),
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.getOrdinal(),
                testCase.getDescription()
        );
    }

    public List<Output> getSampleTestCasesOfContestProblem(Id contestId,
                                                           int contestProblemOrdinal) throws NotFoundException {
        final Contest contest = contestRepository.getById(contestId);
        if (contest == null)
            throw new NotFoundException("Unknown contest");
        final ContestProblem contestProblem = contest.getProblem(contestProblemOrdinal);
        if (contestProblem == null)
            throw new NotFoundException("Unknown contest problem");
        return searchCoreTestCaseService
                .getSampleTestCases(contestProblem.getCoreProblemId(),
                        ContestServiceName.serviceName)
                .stream()
                .map(testCase -> toOutput(testCase, contestId, contestProblemOrdinal))
                .collect(Collectors.toList());
    }
}

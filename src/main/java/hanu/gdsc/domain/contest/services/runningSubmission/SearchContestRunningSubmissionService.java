package hanu.gdsc.domain.contest.services.runningSubmission;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SearchContestRunningSubmissionService {
    private final ContestRepository contestRepository;
    private final SearchRunningSubmissionService
            searchRunningSubmissionService;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id id;
        public Id coderId;
        public Id contestId;
        public int contestProblemOrdinal;
        public String code;
        public ProgrammingLanguage programmingLanguage;
        public DateTime submittedAt;

        public int judgingTestCase;
        public int totalTestCases;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Output toOutput(
            SearchRunningSubmissionService.Output
                    coreOutput,
            Id contestId,
            int contestProblemOrdinal
    ) {
        return Output.builder()
                .id(coreOutput.id)
                .contestId(contestId)
                .contestProblemOrdinal(contestProblemOrdinal)
                .code(coreOutput.code)
                .programmingLanguage(coreOutput.programmingLanguage)
                .submittedAt(coreOutput.submittedAt)
                .judgingTestCase(coreOutput.judgingTestCase)
                .totalTestCases(coreOutput.totalTestCases)
                .build();
    }

    public List<Output>
    getRunningSubmissions(Id contestId,
                          int problemOrdinal,
                          Id coderId,
                          int page,
                          int perPage) throws NotFoundException {
        final Contest contest = contestRepository.getById(contestId);
        if (contest == null)
            throw new NotFoundException("Unknown contest");
        final ContestProblem contestProblem = contest.getProblem(problemOrdinal);
        if (contestProblem == null)
            throw new NotFoundException("Unknown problem ordinal");
        final List<SearchRunningSubmissionService.Output>
                outputs = searchRunningSubmissionService.getByProblemIdAndCoderId(
                contestProblem.getCoreProblemId(),
                coderId,
                page,
                perPage,
                ContestServiceName.serviceName
        );
        return outputs.stream()
                .map(o -> toOutput(o, contestId, contestProblem.getOrdinal()))
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Output getById(Id contestId,
                          int contestProblemOrdinal) throws NotFoundException {
        final Contest contest = contestRepository.getById(contestId);
        if (contest == null)
            throw new NotFoundException("Unknown contest");
        final ContestProblem contestProblem = contest.getProblem(contestProblemOrdinal);
        if (contestProblem == null)
            throw new NotFoundException("Unknown problem ordinal");
        final SearchRunningSubmissionService.Output
                coreSubmission = searchRunningSubmissionService.getById(
                contestProblem.getCoreProblemId(),
                ContestServiceName.serviceName
        );
        return toOutput(coreSubmission, contestId, contestProblemOrdinal);
    }
}
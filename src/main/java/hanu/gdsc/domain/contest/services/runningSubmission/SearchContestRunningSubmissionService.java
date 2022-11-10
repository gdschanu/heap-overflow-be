package hanu.gdsc.domain.contest.services.runningSubmission;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.RunningSubmission;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            RunningSubmission coreOutput,
            Id contestId,
            int contestProblemOrdinal
    ) {
        return Output.builder()
                .id(coreOutput.getId())
                .contestId(contestId)
                .contestProblemOrdinal(contestProblemOrdinal)
                .code(coreOutput.getCode())
                .programmingLanguage(coreOutput.getProgrammingLanguage())
                .submittedAt(coreOutput.getSubmittedAt())
                .judgingTestCase(coreOutput.getJudgingTestCase())
                .totalTestCases(coreOutput.getTotalTestCases())
                .build();
    }

    public List<Output>
    getRunningSubmissions(Id contestId,
                          Integer contestProblemOrdinal,
                          Id coderId,
                          int page,
                          int perPage) throws NotFoundException {
        List<RunningSubmission> runningSubmissions = null;
        if (contestId == null) {
            runningSubmissions = searchRunningSubmissionService
                    .getByProblemIdAndCoderId(
                            null,
                            coderId,
                            page,
                            perPage,
                            ContestServiceName.serviceName
                    );
        }
        if (contestId != null && contestProblemOrdinal == null) {
            final Contest contest = contestRepository.getById(contestId);
            if (contest == null)
                throw new NotFoundException("Unknown contest");
            runningSubmissions = searchRunningSubmissionService
                    .getByProblemIdsAndCoderId(
                            contest.getCoreProblemIds(),
                            coderId,
                            page,
                            perPage,
                            ContestServiceName.serviceName
                    );
        }
        if (contestId != null && contestProblemOrdinal != null) {
            final Contest contest = contestRepository.getById(contestId);
            if (contest == null)
                throw new NotFoundException("Unknown contest");
            final ContestProblem contestProblem = contest.getProblem(contestProblemOrdinal);
            if (contest == null)
                throw new NotFoundException("Unknown contest problem");
            runningSubmissions = searchRunningSubmissionService
                    .getByProblemIdAndCoderId(
                            contestProblem.getCoreProblemId(),
                            coderId,
                            page,
                            perPage,
                            ContestServiceName.serviceName
                    );
        }
        final List<Contest> contests = contestRepository
                .getContestContainsCoreProblemIds(runningSubmissions.stream()
                        .map(submission -> submission.getProblemId())
                        .collect(Collectors.toList()));
        final Map<Id, Contest> coreProblemIdContestMap = new HashMap<>();
        contests.forEach(contest -> {
            for (Id coreProblemId : contest.getCoreProblemIds())
                coreProblemIdContestMap.put(coreProblemId, contest);
        });
        return runningSubmissions.stream()
                .map(runningSubmission -> toOutput(
                        runningSubmission,
                        coreProblemIdContestMap.get(runningSubmission.getProblemId()).getId(),
                        coreProblemIdContestMap.get(runningSubmission.getProblemId())
                                .getProblem(runningSubmission.getProblemId()).getOrdinal()
                )).collect(Collectors.toList());
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
        final RunningSubmission
                coreSubmission = searchRunningSubmissionService.getById(
                contestProblem.getCoreProblemId(),
                ContestServiceName.serviceName
        );
        return toOutput(coreSubmission, contestId, contestProblemOrdinal);
    }
}
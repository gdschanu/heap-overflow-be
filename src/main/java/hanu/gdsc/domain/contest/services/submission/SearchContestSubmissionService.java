package hanu.gdsc.domain.contest.services.submission;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.models.*;
import hanu.gdsc.domain.core_problem.services.submission.SearchSubmissionService;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SearchContestSubmissionService {
    private final SearchSubmissionService searchSubmissionService;
    private final ContestRepository contestRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class FailedTestCaseDetailOutput {
        public Integer failedAtLine;
        public String input;
        public String actualOutput;
        public String expectedOutput;
        public String description;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id id;
        public Id contestId;
        public int contestProblemOrdinal;
        public ProgrammingLanguage programmingLanguage;
        public Millisecond runTime;
        public KB memory;
        public DateTime submittedAt;
        public String code;
        public Status status;
        public FailedTestCaseDetailOutput failedTestCaseDetail;
        public Id coderId;
        public String message;
    }

    private Output toOutput(Submission submission,
                            Id contestId,
                            int contestProblemOrdinal,
                            Contest contest) {
        FailedTestCaseDetailOutput failedTestCaseDetail = null;
        if (contest.ended()) {
            failedTestCaseDetail = submission.getFailedTestCaseDetail() == null ? null :
                    toOutputTestCase(submission.getFailedTestCaseDetail());
        }
        return new Output(
                submission.getId(),
                contestId,
                contestProblemOrdinal,
                submission.getProgrammingLanguage(),
                submission.getRunTime(),
                submission.getMemory(),
                submission.getSubmittedAt(),
                submission.getCode(),
                submission.getStatus(),
                failedTestCaseDetail,
                submission.getCoderId(),
                submission.getMessage()
        );
    }

    private FailedTestCaseDetailOutput toOutputTestCase(FailedTestCaseDetail failedTestCaseDetail) {
        return new FailedTestCaseDetailOutput(
                failedTestCaseDetail.getFailedAtLine(),
                failedTestCaseDetail.getInput(),
                failedTestCaseDetail.getActualOutput(),
                failedTestCaseDetail.getExpectedOutput(),
                failedTestCaseDetail.getDescription()
        );
    }

    public List<Output> get(int page,
                            int perPage,
                            Id contestId,
                            Integer contestProblemOrdinal,
                            Id coderId)
            throws NotFoundException {
        List<Submission> submissions = null;
        if (contestId == null) {
            submissions = searchSubmissionService.getByProblemIdAndCoderId(
                    page,
                    perPage,
                    null,
                    coderId,
                    ContestServiceName.serviceName
            );
        }
        if (contestId != null && contestProblemOrdinal == null) {
            final Contest contest = contestRepository.getById(contestId);
            if (contest == null)
                throw new NotFoundException("Unknown contest");
            submissions = searchSubmissionService.getByProblemIdsAndCoderId(
                    page,
                    perPage,
                    contest.getCoreProblemIds(),
                    coderId,
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
            submissions = searchSubmissionService
                    .getByProblemIdAndCoderId(
                            page,
                            perPage,
                            contestProblem.getCoreProblemId(),
                            coderId,
                            ContestServiceName.serviceName
                    );
        }
        final List<Contest> contests = contestRepository
                .getContestContainsCoreProblemIds(submissions.stream()
                        .map(submission -> submission.getProblemId())
                        .collect(Collectors.toList()));
        final Map<Id, Contest> coreProblemIdContestMap = new HashMap<>();
        contests.forEach(contest -> {
            for (Id coreProblemId : contest.getCoreProblemIds())
                coreProblemIdContestMap.put(coreProblemId, contest);
        });
        return submissions.stream()
                .map(submission -> toOutput(
                        submission,
                        contestId,
                        coreProblemIdContestMap.get(submission.getProblemId())
                                .getProblem(submission.getProblemId()).getOrdinal(),
                        coreProblemIdContestMap.get(submission.getProblemId())
                )).collect(Collectors.toList());
    }
}

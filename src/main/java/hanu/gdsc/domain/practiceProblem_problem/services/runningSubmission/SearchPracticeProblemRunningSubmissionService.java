package hanu.gdsc.domain.practiceProblem_problem.services.runningSubmission;

import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchPracticeProblemRunningSubmissionService {
    private final SearchRunningSubmissionService searchCoreRunningSubmissionService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    public static class Output {
        public Id id;
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
        public DateTime submittedAt;
        public int judgingTestCase;
        public int totalTestCases;
    }

    public Output toOutput(RunningSubmission runningSubmission, Problem problem) {
        return new Output(
                runningSubmission.getId(),
                runningSubmission.getCoderId(),
                problem == null ? null : problem.getId(),
                runningSubmission.getCode(),
                runningSubmission.getProgrammingLanguage(),
                runningSubmission.getSubmittedAt(),
                runningSubmission.getJudgingTestCase(),
                runningSubmission.getTotalTestCases()
        );
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Output
    getById(Id id) {
        final RunningSubmission
                submission = searchCoreRunningSubmissionService.getById(id, PracticeProblemProblemServiceName.serviceName);
        if (submission == null)
            return null;
        final Problem problem = problemRepository.getByCoreProblemProblemId(submission.getProblemId());
        return toOutput(submission, problem);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<Output>
    getRunningSubmissions(Id problemId,
                          Id coderId,
                          int page,
                          int perPage) {
        Id coreProblemId = null;
        if (problemId != null) {
            final Problem problem = problemRepository.getById(problemId);
            if (problem != null) {
                coreProblemId = problem.getCoreProblemId();
            }
        }
        final List<RunningSubmission>
                submissions = searchCoreRunningSubmissionService.getByProblemIdAndCoderId(
                coreProblemId,
                coderId,
                page,
                perPage,
                PracticeProblemProblemServiceName.serviceName
        );
        final List<Problem> problems = problemRepository.findByCoreProblemProblemIds(
                submissions.stream()
                        .map(submission -> submission.getProblemId())
                        .collect(Collectors.toList())
        );
        final Map<Id, Problem> coreProblemIdProblemMap = new HashMap<>();
        problems.forEach(problem -> coreProblemIdProblemMap.put(problem.getCoreProblemId(), problem));
        return submissions.stream()
                .map(submission -> toOutput(
                                submission,
                                coreProblemIdProblemMap.get(submission.getProblemId())
                        )
                ).collect(Collectors.toList());
    }
}

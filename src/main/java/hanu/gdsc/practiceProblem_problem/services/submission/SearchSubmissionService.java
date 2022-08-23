package hanu.gdsc.practiceProblem_problem.services.submission;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SearchSubmissionService {
    private final hanu.gdsc.core_problem.services.submission.SearchSubmissionService searchCoreProblemSubmissionService;
    private final ProblemRepository problemRepository;

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
        public Id problemId;
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

    public List<Output> get(int page, int perPage, Id problemId, Id coderId) {
        Id coreProblemId = null;
        if (problemId != null) {
            Problem problem = problemRepository.getById(problemId);
            if (problem == null)
                throw new NotFoundError("Unknown problem");
            coreProblemId = problem.getCoreProblemProblemId();
        }
        List<Submission> submissions = searchCoreProblemSubmissionService.get(page, perPage, coreProblemId, coderId, ServiceName.serviceName);
        return submissions.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    public Output getById(Id id) {
        Submission submission = searchCoreProblemSubmissionService.getById(id, ServiceName.serviceName);
        return toOutput(submission);
    }

    private Output toOutput(Submission submission) {
        return new Output(
                submission.getId(),
                submission.getProblemId(),
                submission.getProgrammingLanguage(),
                submission.getRunTime(),
                submission.getMemory(),
                submission.getSubmittedAt(),
                submission.getCode(),
                submission.getStatus(),
                submission.getFailedTestCaseDetail() == null ? null :
                        toOutputTestCase(submission.getFailedTestCaseDetail()),
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
}

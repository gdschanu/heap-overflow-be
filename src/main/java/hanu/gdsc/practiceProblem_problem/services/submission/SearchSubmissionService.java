package hanu.gdsc.practiceProblem_problem.services.submission;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SearchSubmissionService {
    private final hanu.gdsc.core_problem.services.submission.SearchSubmissionService searchCoreProblemSubmissionService;
    private final ProblemRepository problemRepository;

    @Builder
    public static class FailedTestCaseDetailOutput {
        public Integer failedAtLine;
        public String input;
        public String actualOutput;
        public String expectedOutput;
        public String description;

    }

    @Builder
    public static class Output {
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
        return Output.builder()
                .problemId(submission.getId())
                .programmingLanguage(submission.getProgrammingLanguage())
                .runTime(submission.getRunTime())
                .memory(submission.getMemory())
                .submittedAt(submission.getSubmittedAt())
                .code(submission.getCode())
                .status(submission.getStatus())
                .failedTestCaseDetail(
                        submission.getFailedTestCaseDetail() == null ? null :
                                toOutputTestCase(submission.getFailedTestCaseDetail())
                )
                .coderId(submission.getCoderId())
                .message(submission.getMessage())
                .build();
    }

    private FailedTestCaseDetailOutput toOutputTestCase(FailedTestCaseDetail failedTestCaseDetail) {
        return FailedTestCaseDetailOutput.builder()
                .failedAtLine(failedTestCaseDetail.getFailedAtLine())
                .input(failedTestCaseDetail.getInput())
                .actualOutput(failedTestCaseDetail.getActualOutput())
                .expectedOutput(failedTestCaseDetail.getExpectedOutput())
                .description(failedTestCaseDetail.getDescription())
                .build();
    }
}

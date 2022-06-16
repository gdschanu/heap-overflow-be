package hanu.gdsc.core_problem.services.submission;

import hanu.gdsc.core_problem.domains.FailedTestCaseDetail;
import hanu.gdsc.core_problem.domains.Submission;
import hanu.gdsc.core_problem.repositories.submission.SubmissionRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchSubmissionServiceImpl implements SearchSubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public List<Output> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate) {
        List<Submission> submissions = submissionRepository.get(page, perPage, problemId, coderId, serviceToCreate);
        return submissions.stream()
                .map(s -> toOutput(s))
                .collect(Collectors.toList());
    }

    @Override
    public Output getById(Id id, String serviceToCreate) {
        Submission submission = submissionRepository.getById(id, serviceToCreate);
        if (submission == null) {
            throw new NotFoundError("Submission not found");
        }
        return toOutput(submission);
    }

    private static Output toOutput(Submission submission) {
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

    private static FailedTestCaseDetailOutput toOutputTestCase(FailedTestCaseDetail failedTestCaseDetail) {
        return FailedTestCaseDetailOutput.builder()
                .failedAtLine(failedTestCaseDetail.getFailedAtLine())
                .input(failedTestCaseDetail.getInput())
                .actualOutput(failedTestCaseDetail.getActualOutput())
                .expectedOutput(failedTestCaseDetail.getExpectedOutput())
                .description(failedTestCaseDetail.getDescription())
                .build();
    }
}

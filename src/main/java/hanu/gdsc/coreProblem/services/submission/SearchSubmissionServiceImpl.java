package hanu.gdsc.coreProblem.services.submission;

import hanu.gdsc.coreProblem.domains.FailedTestCaseDetail;
import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new BusinessLogicError("Not found this submission", "NOT_FOUND");
        }
        return toOutput(submission);
    }

    private static Output toOutput(Submission submission) {
        if (submission.getFailedTestCaseDetail() == null) {
            return Output.builder()
                .problemId(submission.getProblemId())
                .programmingLanguage(submission.getProgrammingLanguage())
                .runTime(submission.getRunTime())
                .memory(submission.getMemory())
                .submittedAt(submission.getSubmittedAt())
                .code(submission.getCode())
                .status(submission.getStatus())
                .failedTestCaseDetail(null)
                .build();
        }
        return Output.builder()
                .problemId(submission.getId())
                .programmingLanguage(submission.getProgrammingLanguage())
                .runTime(submission.getRunTime())
                .memory(submission.getMemory())
                .submittedAt(submission.getSubmittedAt())
                .code(submission.getCode())
                .status(submission.getStatus())
                .failedTestCaseDetail(toOutputTestCase(submission.getFailedTestCaseDetail()))
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

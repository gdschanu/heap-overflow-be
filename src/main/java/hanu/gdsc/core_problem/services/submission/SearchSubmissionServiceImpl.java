package hanu.gdsc.core_problem.services.submission;

import hanu.gdsc.core_problem.domains.FailedTestCaseDetail;
import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.domains.Submission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.core_problem.repositories.submission.SubmissionRepository;
import hanu.gdsc.practiceProblem_problem.errors.SubmissionIsBeingJudgedError;
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
    private SubmissionRepository submissionRepository;
    private RunningSubmissionRepository runningSubmissionRepository;

    @Override
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate) {
        return submissionRepository.get(page, perPage, problemId, coderId, serviceToCreate);
    }

    @Override
    public Submission getById(Id id, String serviceToCreate) {
        Submission submission = submissionRepository.getById(id, serviceToCreate);
        if (submission == null) {
            RunningSubmission runningSubmission = runningSubmissionRepository.getById(id, serviceToCreate);
            if (runningSubmission != null) {
                throw new SubmissionIsBeingJudgedError();
            }
            throw new NotFoundError("Submission not found");
        }
        return submission;
    }
}

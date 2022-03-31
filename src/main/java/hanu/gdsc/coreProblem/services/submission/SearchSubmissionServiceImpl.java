package hanu.gdsc.coreProblem.services.submission;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchSubmissionServiceImpl implements SearchSubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission getById(Id id) {
        return null;
    }

    @Override
    public List<Submission> getPracticeSubmissions(Id problemId) {
        List<Submission> submissions = submissionRepository.getPracticeSubmissions(problemId);
        if (submissions == null) {
            throw new BusinessLogicError("Không tìm thấy submission", "NOT_FOUND");
        }
        return submissions;
    }
}

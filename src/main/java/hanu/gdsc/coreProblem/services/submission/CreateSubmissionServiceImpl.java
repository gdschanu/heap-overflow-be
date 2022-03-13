package hanu.gdsc.coreProblem.services.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.repositories.SubmissionRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class CreateSubmissionServiceImpl implements CreateSubmissionService{
    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Id create(Input input) {
        Submission submission = Submission.create(
            input.problemId,
            input.programmingLanguage,
            input.runTime,
            input.memory,
            input.submittedAt,
            input.code, 
            input.status,
            input.failedTestCase
        );
        submissionRepository.create(submission);
        return submission.getId();
    }
    
}

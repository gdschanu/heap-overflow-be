package hanu.gdsc.coreProblem.services.submissionsCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class CreateSubmissionCountServiceImpl implements CreateSubmissionCountService{
    @Autowired
    private SubmissionCountRepository submissionCountRepository;    

    @Override
    public void create(Id problemId) {
        SubmissionCount submissionCount = SubmissionCount.create(problemId);
        submissionCountRepository.create(submissionCount);
    }   
}

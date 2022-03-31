package hanu.gdsc.coreProblem.services.submissionsCount;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.SubmissionCountRepository;


@Service
public class UpdateSubmissionCountServiceImpl implements UpdateSubmissionCountService{
    @Autowired
    private SubmissionCountRepository submissionCountRepository;
    @Autowired
    private SearchSubmissionCountService searchSubmissionCountService;

    @Override
    @Transactional
    public void update(Input input) {
        SubmissionCount submissionCount = searchSubmissionCountService.getById(input.problemId);
        if(input.status.equals(Status.AC)) {
            submissionCount.updateACsCount();
            submissionCount.updateSubmissionsCount();
        } else {
            submissionCount.updateSubmissionsCount();
        }
        submissionCountRepository.update(submissionCount);
    }
}

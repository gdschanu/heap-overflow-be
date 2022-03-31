package hanu.gdsc.coreProblem.services.submissionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;


@Service
public class CreateSubmissionEventServiceImpl implements CreateSubmissionEventService {  
    @Autowired
    private SubmissionEventRepository submissionEventRepository;

    @Override
    public void create(Input input) {
        SubmissionEvent submissionEvent = SubmissionEvent.create(
            input.problemId,
            input.status
        );
        submissionEventRepository.create(submissionEvent);
        
    }
    
}

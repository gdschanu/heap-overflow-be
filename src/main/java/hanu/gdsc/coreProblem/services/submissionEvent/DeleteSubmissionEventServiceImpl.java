package hanu.gdsc.coreProblem.services.submissionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class DeleteSubmissionEventServiceImpl implements DeleteSubmissionEventService{
    @Autowired
    private SubmissionEventRepository submissionEventRepository;

    @Override
    public void deleteById(Id id) {
       submissionEventRepository.delete(id);
        
    }
    
}

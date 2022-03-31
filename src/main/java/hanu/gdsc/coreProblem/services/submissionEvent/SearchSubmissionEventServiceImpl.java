package hanu.gdsc.coreProblem.services.submissionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.repositories.SubmissionEventRepository;

@Service
public class SearchSubmissionEventServiceImpl implements SearchSubmissionEventService{
    @Autowired
    private SubmissionEventRepository submissionEventRepository;
    @Override
    public SubmissionEvent getSubmissionEvent() {
        SubmissionEvent submissionEvent = submissionEventRepository.getSubmissionEvent();
        if (submissionEvent == null) {
            return null;
        }
        return submissionEvent;
    }
    
}

package hanu.gdsc.coreProblem.services.submissionsCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SearchSubmissionCountServiceImpl implements SearchSubmissionCountService{
    @Autowired
    private SubmissionCountRepository submissionCountRepository;
    @Override
    public SubmissionCount getByProblemId(Id id, String serviceToCreate) {
        SubmissionCount submissionCount = submissionCountRepository.getByProblemId(id);
        if (submissionCount == null) {
            throw new BusinessLogicError("Not found submissionCount", "NOT_FOUND");
        }
        return submissionCount;
    }
    
}

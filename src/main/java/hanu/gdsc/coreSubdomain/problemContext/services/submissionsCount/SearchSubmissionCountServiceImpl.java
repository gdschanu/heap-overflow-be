package hanu.gdsc.coreSubdomain.problemContext.services.submissionsCount;

import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionCount;
import hanu.gdsc.coreSubdomain.problemContext.repositories.submissionCount.SubmissionCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SearchSubmissionCountServiceImpl implements SearchSubmissionCountService{
    @Autowired
    private SubmissionCountRepository submissionCountRepository;
    @Override
    public SubmissionCount getByProblemId(Id id, String serviceToCreate) {
        SubmissionCount submissionCount = submissionCountRepository.getByProblemId(id, serviceToCreate);
        if (submissionCount == null) {
            throw new BusinessLogicError("Not found submissionCount", "NOT_FOUND");
        }
        return submissionCount;
    }
}

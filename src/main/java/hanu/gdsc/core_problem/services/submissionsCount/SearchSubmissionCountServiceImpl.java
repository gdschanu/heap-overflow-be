package hanu.gdsc.core_problem.services.submissionsCount;

import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.core_problem.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchSubmissionCountServiceImpl implements SearchSubmissionCountService{
    @Autowired
    private SubmissionCountRepository submissionCountRepository;

    @Override
    public SubmissionCount getByProblemId(Id id, String serviceToCreate) throws NotFoundException {
        SubmissionCount submissionCount = submissionCountRepository.getByProblemId(id, serviceToCreate);
        if (submissionCount == null) {
            throw new NotFoundException("Not found submissionCount");
        }
        return submissionCount;
    }

    @Override
    public List<SubmissionCount> getByProblemIds(List<Id> ids, String serviceToCreate) {
        return submissionCountRepository.getByProblemIds(ids, serviceToCreate);
    }
}

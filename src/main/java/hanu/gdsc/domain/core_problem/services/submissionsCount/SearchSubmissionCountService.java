package hanu.gdsc.domain.core_problem.services.submissionsCount;

import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

import java.util.List;

public interface SearchSubmissionCountService {
    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate) throws NotFoundException;

    public List<SubmissionCount> getByProblemIds(List<Id> ids, String serviceToCreate);
}

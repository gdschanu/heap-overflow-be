package hanu.gdsc.core_problem.services.submissionsCount;

import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;

public interface SearchSubmissionCountService {
    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate) throws NotFoundException;
}

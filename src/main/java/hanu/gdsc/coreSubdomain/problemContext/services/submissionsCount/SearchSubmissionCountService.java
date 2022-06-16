package hanu.gdsc.coreSubdomain.problemContext.services.submissionsCount;

import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

public interface SearchSubmissionCountService {
    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate);
}

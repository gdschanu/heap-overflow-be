package hanu.gdsc.coreProblem.services.submissionsCount;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

public interface SearchSubmissionCountService {
    public SubmissionCount getById(Id id);
}

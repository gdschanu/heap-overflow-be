package hanu.gdsc.core_problem.services.runningSubmission;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.share.domains.Id;

public interface SearchRunningSubmissionService {
    public RunningSubmission getByIdAndCoderId(Id id, Id coderId);
}

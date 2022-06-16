package hanu.gdsc.coreSubdomain.problemContext.repositories.runningSubmission;

import hanu.gdsc.coreSubdomain.problemContext.domains.RunningSubmission;
import hanu.gdsc.share.domains.Id;

public interface RunningSubmissionRepository {
    public void create(RunningSubmission runningSubmission);

    public RunningSubmission claim();

    public void delete(Id id);

    public void updateClaimed(RunningSubmission runningSubmission);

    public RunningSubmission getById(Id id);
}

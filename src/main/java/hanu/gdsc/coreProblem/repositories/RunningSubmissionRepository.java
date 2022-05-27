package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.RunningSubmission;
import hanu.gdsc.share.domains.Id;

public interface RunningSubmissionRepository {
    public void create(RunningSubmission runningSubmission);

    public RunningSubmission claim();

    public void delete(Id id);

    public void updateClaimed(RunningSubmission runningSubmission);
}

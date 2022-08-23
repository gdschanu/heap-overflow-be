package hanu.gdsc.core_problem.repositories.runningSubmission;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface RunningSubmissionRepository {
    public void create(RunningSubmission runningSubmission);

    public RunningSubmission claim();

    public void delete(Id id);

    public void updateClaimed(RunningSubmission runningSubmission);

    public RunningSubmission getById(Id id, String serviceToCreate);

    public RunningSubmission getByIdAndCoderId(Id id, Id coderId, String serviceToCreate);

    public List<RunningSubmission> getByCoderId(int page, int perPage, Id coderId, String serviceToCreate);
}

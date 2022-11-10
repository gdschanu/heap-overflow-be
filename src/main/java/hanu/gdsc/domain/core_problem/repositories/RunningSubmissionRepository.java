package hanu.gdsc.domain.core_problem.repositories;

import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface RunningSubmissionRepository {
    public void create(RunningSubmission runningSubmission);

    public RunningSubmission getById(Id id, String serviceToCreate);

    public List<RunningSubmission> getByProblemIdAndCoderId(Id problemId,
                                                            Id coderId,
                                                            int page,
                                                            int perPage,
                                                            String serviceToCreate);

    public List<RunningSubmission> getByProblemIdsAndCoderId(List<Id> problemIds,
                                                             Id coderId,
                                                             int page,
                                                             int perPage,
                                                             String serviceToCreate);
}

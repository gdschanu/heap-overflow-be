package hanu.gdsc.domain.core_problem.repositories;

import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface SubmissionCountRepository {
    public void create(SubmissionCount submission);

    public void createMany(List<SubmissionCount> submissions);

    public void update(SubmissionCount submission);

    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate);

    public SubmissionCount getByProblemId(Id problemId);

    public List<SubmissionCount> getByProblemIds(List<Id> problemIds, String serviceToCreate);
}

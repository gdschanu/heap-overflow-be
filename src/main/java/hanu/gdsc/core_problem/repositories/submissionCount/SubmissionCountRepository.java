package hanu.gdsc.core_problem.repositories.submissionCount;

import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SubmissionCountRepository {
    public void create(SubmissionCount submission);

    public void createMany(List<SubmissionCount> submissions);

    public void update(SubmissionCount submission);

    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate);

    public SubmissionCount getByProblemId(Id problemId);
}

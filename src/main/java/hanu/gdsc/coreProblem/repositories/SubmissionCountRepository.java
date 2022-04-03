package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SubmissionCountRepository {
    public void create(SubmissionCount submission);

    public void update(SubmissionCount submission);

    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate);

}

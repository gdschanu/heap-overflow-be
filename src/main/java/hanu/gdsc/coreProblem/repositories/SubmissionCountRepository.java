package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.share.domains.Id;

public interface SubmissionCountRepository {
    public SubmissionCount getById(Id id);
    public void create(SubmissionCount submission);
    public void update(SubmissionCount submission);
}

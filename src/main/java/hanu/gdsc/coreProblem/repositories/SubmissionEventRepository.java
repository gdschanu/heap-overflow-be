package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.share.domains.Id;

public interface SubmissionEventRepository {
    public SubmissionEvent getSubmissionEvent();
    public void create(SubmissionEvent submissionEvent);
    public void delete(Id id);
}

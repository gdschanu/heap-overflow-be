package hanu.gdsc.core_problem.repositories.submissionEvent;

import hanu.gdsc.core_problem.domains.SubmissionEvent;
import hanu.gdsc.share.domains.Id;

public interface SubmissionEventRepository {
    public SubmissionEvent getSubmissionEvent();

    public void create(SubmissionEvent submissionEvent);

    public void delete(Id id);

}

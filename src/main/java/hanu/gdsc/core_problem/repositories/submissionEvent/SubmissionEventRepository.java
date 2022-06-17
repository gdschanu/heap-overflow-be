package hanu.gdsc.core_problem.repositories.submissionEvent;

import hanu.gdsc.core_problem.domains.SubmissionEvent;
import hanu.gdsc.share.domains.Id;

public interface SubmissionEventRepository {
    public SubmissionEvent getSubmissionEvent();

    public void save(SubmissionEvent submissionEvent);

    public void delete(Id id);

}

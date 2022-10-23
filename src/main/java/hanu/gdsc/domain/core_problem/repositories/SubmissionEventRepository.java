package hanu.gdsc.domain.core_problem.repositories;

import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.share.models.Id;

public interface SubmissionEventRepository {
    public SubmissionEvent getSubmissionEvent();

    public void save(SubmissionEvent submissionEvent);

    public void delete(Id id);

}

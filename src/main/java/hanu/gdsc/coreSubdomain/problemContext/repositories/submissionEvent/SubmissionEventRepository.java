package hanu.gdsc.coreSubdomain.problemContext.repositories.submissionEvent;

import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionEvent;
import hanu.gdsc.share.domains.Id;

public interface SubmissionEventRepository {
    public SubmissionEvent getSubmissionEvent();

    public void create(SubmissionEvent submissionEvent);

    public void delete(Id id);

}

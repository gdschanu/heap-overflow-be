package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class SubmissionEvent extends IdentifiedDomainObject {
    private Id problemId;
    private Status status;
    private DateTime createdAt;

    public SubmissionEvent(Id id, Id problemId, Status status, DateTime createdAt) {
        super(id);
        this.problemId = problemId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static SubmissionEvent create(Id problemId, Status status) {
        return new SubmissionEvent(
                Id.generateRandom(),
                problemId,
                status,
                DateTime.now()
        );
    }

    public Id getProblemId() {
        return problemId;
    }

    public Status getStatus() {
        return status;
    }
}

package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class SubmissionEvent extends IdentifiedDomainObject {
    private Id problemId;
    private Status status;

    private SubmissionEvent(Id id, Id problemId, Status status) {
        super(id);
        this.problemId = problemId;
        this.status = status;
    }

    public static SubmissionEvent create(Id problemId, Status status) {
        return new SubmissionEvent(
                Id.generateRandom(),
                problemId,
                status
        );
    }

    public Id getProblemId() {
        return problemId;
    }

    public Status getStatus() {
        return status;
    }
}

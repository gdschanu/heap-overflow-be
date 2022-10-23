package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;

public class SubmissionEvent extends IdentifiedDomainObject {
    private Id problemId;
    private Status status;

    private Id coderId;

    private SubmissionEvent(Id id, Id problemId, Status status, Id coderId) {
        super(id);
        this.problemId = problemId;
        this.status = status;
        this.coderId = coderId;
    }

    public static SubmissionEvent create(Id problemId, Status status, Id coderId) {
        return new SubmissionEvent(
                Id.generateRandom(),
                problemId,
                status,
                coderId
        );
    }

    public Id getProblemId() {
        return problemId;
    }

    public Status getStatus() {
        return status;
    }

    public Id getCoderId() {
        return coderId;
    }
}

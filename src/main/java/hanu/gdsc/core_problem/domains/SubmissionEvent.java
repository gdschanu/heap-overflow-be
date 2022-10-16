package hanu.gdsc.core_problem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

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

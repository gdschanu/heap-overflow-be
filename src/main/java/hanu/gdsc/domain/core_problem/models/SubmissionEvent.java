package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;
import lombok.Data;
import lombok.NoArgsConstructor;
public class SubmissionEvent {
    private Id problemId;
    private Status status;
    private Id coderId;

    public SubmissionEvent() {
    };

    private SubmissionEvent(Id problemId, Status status, Id coderId) {
        this.problemId = problemId;
        this.status = status;
        this.coderId = coderId;
    }

    public static SubmissionEvent create(Id problemId, Status status, Id coderId) {
        return new SubmissionEvent(
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

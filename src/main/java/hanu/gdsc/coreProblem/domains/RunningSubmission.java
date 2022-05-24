package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class RunningSubmission extends IdentifiedDomainObject {
    private Id problemId;
    private DateTime submittedAt;
    private Id coderId;

    private RunningSubmission(Id id, Id problemId, DateTime submittedAt, Id coderId) {
        super(id);
        this.problemId = problemId;
        this.submittedAt = submittedAt;
        this.coderId = coderId;
    }

    public static RunningSubmission create(Id problemId, Id coderId) {
        return new RunningSubmission(
                Id.generateRandom(),
                problemId,
                DateTime.now(),
                coderId
        );
    }
}

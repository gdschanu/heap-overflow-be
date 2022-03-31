package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class SubmissionCount extends IdentitifedVersioningDomainObject {
    private Id problemId;
    private int ACsCount;
    private int submissionsCount;

    public SubmissionCount(Id id, long version, Id problemId, int ACsCount, int submissionsCount) {
        super(id, version);
        this.problemId = problemId;
        this.ACsCount = ACsCount;
        this.submissionsCount = submissionsCount;
    }

    public static SubmissionCount create(Id problemId) {
        return new SubmissionCount(
                Id.generateRandom(),
                0,
                problemId,
                0,
                0
        );
    }

    public void updateACsCount() {
        this.ACsCount += 1;
    }

    public void updateSubmissionsCount() {
        this.submissionsCount += 1;
    }

    public Id getProblemId() {
        return problemId;
    }

    public int getACsCount() {
        return ACsCount;
    }

    public int getSubmissionsCount() {
        return submissionsCount;
    }
}

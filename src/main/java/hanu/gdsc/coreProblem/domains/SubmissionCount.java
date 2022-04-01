package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class SubmissionCount extends IdentitifedVersioningDomainObject {
    private Id problemId;
    private long ACsCount;
    private long submissionsCount;

    public SubmissionCount(Id id, long version, Id problemId, long ACsCount, long submissionsCount) {
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

    public long getACsCount() {
        return ACsCount;
    }

    public long getSubmissionsCount() {
        return submissionsCount;
    }
}

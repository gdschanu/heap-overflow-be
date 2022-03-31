package hanu.gdsc.coreProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class SubmissionCount extends VersioningDomainObject {
    private Id problemId;
    private int ACsCount;
    private int submissionsCount;

    public SubmissionCount(long version, Id problemId, int ACsCount, int submissionsCount) {
        super(version);
        this.problemId = problemId;
        this.ACsCount = ACsCount;
        this.submissionsCount = submissionsCount;
    }

    public static SubmissionCount create(Id problemId) {
        return new SubmissionCount(
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

package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;

public class SubmissionCount extends VersioningDomainObject {
    private Id problemId;
    private long ACsCount;
    private long submissionsCount;
    private String serviceToCreate;

    private SubmissionCount(long version, Id problemId, long ACsCount, long submissionsCount, String serviceToCreate) {
        super(version);
        this.problemId = problemId;
        this.ACsCount = ACsCount;
        this.submissionsCount = submissionsCount;
        this.serviceToCreate = serviceToCreate;
    }

    public int acceptance() {
        return (int) ((ACsCount / (double)submissionsCount) * 100);
    }

    public static SubmissionCount create(Id problemId, String serviceToCreate) {
        return new SubmissionCount(
                0,
                problemId,
                0,
                0,
                serviceToCreate
        );
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

    public void increaseACsCount() {
        this.ACsCount += 1;
    }

    public void increaseSubmissionsCount() {
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

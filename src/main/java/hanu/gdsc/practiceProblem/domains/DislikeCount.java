package hanu.gdsc.practiceProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class DislikeCount extends VersioningDomainObject {
    private Id problemId;
    private long dislikeCount;

    public DislikeCount(long version, Id problemId, long dislikeCount) {
        super(version);
        this.problemId = problemId;
        this.dislikeCount = dislikeCount;
    }

    public Id getProblemId() {
        return problemId;
    }

    public long getDislikeCount() {
        return dislikeCount;
    }
}

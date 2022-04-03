package hanu.gdsc.practiceProblem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class LikeCount extends VersioningDomainObject {
    private Id problemId;
    private long likeCount;

    public LikeCount(long version, Id problemId, long likeCount) {
        super(version);
        this.problemId = problemId;
        this.likeCount = likeCount;
    }

    public static LikeCount create(Id problemId) {
        return new LikeCount(
                0,
                problemId,
                0
        );
    }

    public Id getProblemId() {
        return problemId;
    }

    public long getLikeCount() {
        return likeCount;
    }
}

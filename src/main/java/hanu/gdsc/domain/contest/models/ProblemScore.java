package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;

public class ProblemScore extends IdentitifedVersioningDomainObject {
    private Id problemId;
    private int score;
    private int tryCount;

    private ProblemScore(Id id, long version) {
        super(id, version);
    }

    public Id getProblemId() {
        return problemId;
    }

    public int getScore() {
        return score;
    }

    public int getTryCount() {
        return tryCount;
    }
}

package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

public class Problem extends IdentitifedDomainObject {
    private int ordinal;
    private Id coreProblemId;
    private int score;

    public Problem(Id id, long version, int ordinal, Id coreProblemId, int score) {
        super(id, version);
        this.ordinal = ordinal;
        this.coreProblemId = coreProblemId;
        this.score = score;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public Id getCoreProblemId() {
        return coreProblemId;
    }

    public int getScore() {
        return score;
    }
}

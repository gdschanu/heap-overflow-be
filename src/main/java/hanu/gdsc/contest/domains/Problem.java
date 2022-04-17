package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Problem extends IdentitifedVersioningDomainObject {
    private int ordinal;
    private Id coreProblemId;
    private int score;

    public Problem(Id id, long version, int ordinal, Id coreProblemId, int score) {
        super(id, version);
        this.ordinal = ordinal;
        this.coreProblemId = coreProblemId;
        this.score = score;
    }

    public static Problem create(
            int ordinal,
            Id coreProblemId,
            int score
    ) {
        return new Problem(
                Id.generateRandom(),
                0,
                ordinal,
                coreProblemId,
                score
        );
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

package hanu.gdsc.contest_contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.VersioningDomainObject;

public class ContestProblem extends VersioningDomainObject {
    private int ordinal;
    private Id coreProblemId;
    private int score;

    private ContestProblem(long version, int ordinal, Id coreProblemId, int score) {
        super(version);
        this.ordinal = ordinal;
        this.coreProblemId = coreProblemId;
        this.score = score;
    }

    public static ContestProblem create(
            int ordinal,
            Id coreProblemId,
            int score
    ) {
        return new ContestProblem(
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

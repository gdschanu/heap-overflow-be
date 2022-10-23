package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;

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

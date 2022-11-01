package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;

public class ContestProblem {
    private int ordinal;
    private Id coreProblemId;
    private double score;

    private ContestProblem(int ordinal, Id coreProblemId, int score) {
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

    public double getScore() {
        return score;
    }
}

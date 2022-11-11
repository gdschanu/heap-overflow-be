package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import lombok.Getter;

@Getter
public class ContestProblem {
    private int ordinal;
    private Id coreProblemId;
    private double score;
    private boolean allowPartialScore;

    public ContestProblem(int ordinal, Id coreProblemId, double score, boolean allowPartialScore) {
        this.ordinal = ordinal;
        this.coreProblemId = coreProblemId;
        this.score = score;
        this.allowPartialScore = allowPartialScore;
    }

    public static ContestProblem create(
            int ordinal,
            Id coreProblemId,
            int score,
            boolean allowPartialScore
    ) {
        return new ContestProblem(
                ordinal,
                coreProblemId,
                score,
                allowPartialScore
        );
    }
}

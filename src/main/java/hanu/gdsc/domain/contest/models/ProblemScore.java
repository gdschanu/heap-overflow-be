package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProblemScore {
    private int problemOrdinal;
    private double score;

    public int getProblemOrdinal() {
        return problemOrdinal;
    }

    public double getScore() {
        return score;
    }
}

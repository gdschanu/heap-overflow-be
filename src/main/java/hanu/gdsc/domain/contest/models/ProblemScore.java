package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.share.models.Id;

public class ProblemScore {
    private int problemOrdinal;
    private double score;
    private int tryCount;

    public int getProblemOrdinal() {
        return problemOrdinal;
    }

    public double getScore() {
        return score;
    }

    public int getTryCount() {
        return tryCount;
    }
}

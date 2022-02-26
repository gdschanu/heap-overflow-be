package hanu.gdsc.contest.domains.participant;

public class ContestProblemScore {
    private ID contestProblemId;
    private double score;
    

    /**
     * @return ID return the contestProblemId
     */
    public ID getContestProblemId() {
        return contestProblemId;
    }

    /**
     * @param contestProblemId the contestProblemId to set
     */
    public void setContestProblemId(ID contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    /**
     * @return double return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

}

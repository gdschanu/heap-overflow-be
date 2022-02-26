package hanu.gdsc.contest.domains.contestProblem;

public class ContestProblem {
    private ID id;
    private int ordinal;
    private ID problemId;
    private double score;
    

    /**
     * @return ID return the id
     */
    public ID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * @return int return the ordinal
     */
    public int getOrdinal() {
        return ordinal;
    }

    /**
     * @param ordinal the ordinal to set
     */
    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    /**
     * @return ID return the problemId
     */
    public ID getProblemId() {
        return problemId;
    }

    /**
     * @param problemId the problemId to set
     */
    public void setProblemId(ID problemId) {
        this.problemId = problemId;
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

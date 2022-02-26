package hanu.gdsc.contest.domains.participant;

public class Participant {
    private ID id;
    private ID coderId;
    private int rank;
    private double contestProblemScore;

    

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
     * @return ID return the coderId
     */
    public ID getCoderId() {
        return coderId;
    }

    /**
     * @param coderId the coderId to set
     */
    public void setCoderId(ID coderId) {
        this.coderId = coderId;
    }

    /**
     * @return int return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return double return the contestProblemScore
     */
    public double getContestProblemScore() {
        return contestProblemScore;
    }

    /**
     * @param contestProblemScore the contestProblemScore to set
     */
    public void setContestProblemScore(double contestProblemScore) {
        this.contestProblemScore = contestProblemScore;
    }

}

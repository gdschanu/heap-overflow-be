package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant extends IdentitifedDomainObject {
    private Id coderId;
    private Id contestId;
    private int rank;
    private List<ProblemScore> problemScores;

    public Participant(Id id, long version, Id coderId, Id contestId, int rank, List<ProblemScore> problemScores) {
        super(id, version);
        this.coderId = coderId;
        this.contestId = contestId;
        this.rank = rank;
        this.problemScores = problemScores;
    }

    public static Participant create(Id coderId, Contest contest) {
        if (!contest.ended()) {
            throw new BusinessLogicError("Không thể thêm thí sinh, kì thi đã kết thúc.");
        }
        return new Participant(
                Id.generateRandom(),
                0,
                coderId,
                contest.getId(),
                0,
                new ArrayList<>()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return coderId.equals(that.coderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coderId);
    }

    public Id getCoderId() {
        return coderId;
    }

    public Id getContestId() {
        return contestId;
    }

    public int getRank() {
        return rank;
    }

    public List<ProblemScore> getProblemScores() {
        return problemScores;
    }
}

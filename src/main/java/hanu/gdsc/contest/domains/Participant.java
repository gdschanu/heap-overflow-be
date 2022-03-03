package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

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

    public static Participant create(Id coderId, Id contestId) {
        return new Participant(
                Id.generateRandom(),
                0,
                coderId,
                contestId,
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
}

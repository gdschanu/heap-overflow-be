package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant extends IdentitifedDomainObject {
    private Id coderId;
    private int rank;
    private List<ProblemScore> problemScores;

    private Participant(Id id, long version, Id coderId, int rank, List<ProblemScore> problemScores) {
        super(id, version);
        this.coderId = coderId;
        this.rank = rank;
        this.problemScores = problemScores;
    }

    public static Participant create(Id coderId) {
        return new Participant(
                Id.generateRandom(),
                0,
                coderId,
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

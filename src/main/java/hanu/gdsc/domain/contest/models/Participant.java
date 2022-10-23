package hanu.gdsc.domain.contest.models;

import hanu.gdsc.domain.contest.exception.ContestEndedException;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participant extends VersioningDomainObject {
    private Id coderId;
    private Id contestId;
    private int rank;
    private List<ProblemScore> problemScores;
    private DateTime createdAt;

    private Participant(long version,
                        Id coderId,
                        Id contestId,
                        int rank,
                        List<ProblemScore> problemScores,
                        DateTime createdAt) {
        super(version);
        this.coderId = coderId;
        this.contestId = contestId;
        this.rank = rank;
        this.problemScores = problemScores;
        this.createdAt = createdAt;
    }

    public static Participant create(Id coderId, Contest contest) throws ContestEndedException {
        if (contest.ended()) {
            throw new ContestEndedException();
        }
        return new Participant(
                0,
                coderId,
                contest.getId(),
                0,
                new ArrayList<>(),
                DateTime.now()
        );
    }

    public DateTime getCreatedAt() {
        return createdAt;
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

package hanu.gdsc.domain.practiceProblem_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;
import hanu.gdsc.domain.share.models.DateTime;

public class Problem extends IdentitifedVersioningDomainObject {
    private Id coreProblemProblemId;
    private Difficulty difficulty;
    private DateTime createdAt;
    private Integer point;


    private Problem(Id id,
                    long version,
                    Id coreProblemProblemId,
                    Difficulty difficulty,
                    DateTime createdAt,
                    Integer point) {
        super(id, version);
        this.coreProblemProblemId = coreProblemProblemId;
        this.difficulty = difficulty;
        this.createdAt = createdAt;
        this.point = point;
    }

    public static Problem create(Id coreProlemProblemId, Difficulty difficulty, Integer point) {
        return new Problem(
                Id.generateRandom(),
                0,
                coreProlemProblemId,
                difficulty,
                DateTime.now(),
                point);
    }

    public Id getCoreProblemProblemId() {
        return coreProblemProblemId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getPoint() {
        return point;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

}

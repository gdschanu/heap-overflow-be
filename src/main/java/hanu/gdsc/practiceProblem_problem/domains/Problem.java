package hanu.gdsc.practiceProblem_problem.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Problem extends IdentitifedVersioningDomainObject {
    private Id coreProblemProblemId;
    private Difficulty difficulty;


    private Problem(Id id,
                    long version,
                    Id coreProblemProblemId,
                    Difficulty difficulty) {
        super(id, version);
        this.coreProblemProblemId = coreProblemProblemId;
        this.difficulty = difficulty;
    }

    public static Problem create(Id coreProlemProblemId, Difficulty difficulty) {
        return new Problem(
                Id.generateRandom(),
                0,
                coreProlemProblemId,
                difficulty);
    }

    public Id getCoreProblemProblemId() {
        return coreProblemProblemId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

}

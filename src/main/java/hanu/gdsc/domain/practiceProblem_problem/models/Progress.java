package hanu.gdsc.domain.practiceProblem_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.VersioningDomainObject;

public class Progress extends VersioningDomainObject {
    private Id coderId;
    private int solvedEasyProblems;
    private int solvedHardProblems;
    private int solvedMediumProblems;

    private Progress(Id coderId, int solvedEasyProblems, int solvedHardProblems, int solvedMediumProblems, long version) {
        super(version);
        this.coderId = coderId;
        this.solvedEasyProblems = solvedEasyProblems;
        this.solvedHardProblems = solvedHardProblems;
        this.solvedMediumProblems = solvedMediumProblems;
    }

    public static Progress create(Id coderId) {
        return new Progress(
                coderId,
                0,
                0,
                0,
                0
        );
    }

    public void update(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) solvedEasyProblems++;
        if (difficulty == Difficulty.MEDIUM) solvedMediumProblems++;
        if (difficulty == Difficulty.HARD) solvedHardProblems++;
    }

    public Id getCoderId() {
        return coderId;
    }

    public int getSolvedEasyProblems() {
        return solvedEasyProblems;
    }

    public int getSolvedHardProblems() {
        return solvedHardProblems;
    }

    public int getSolvedMediumProblems() {
        return solvedMediumProblems;
    }
}

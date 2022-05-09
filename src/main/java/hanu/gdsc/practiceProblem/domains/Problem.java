package hanu.gdsc.practiceProblem.domains;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Problem extends IdentitifedVersioningDomainObject {
    private Id coreProblemProblemId;
    private List<Id> categoryIds;
    private Difficulty difficulty;


    private Problem(Id id, long version, Id coreProblemProblemId, List<Id> categoryIds,
                   Difficulty difficulty) {
        super(id, version);
        this.coreProblemProblemId = coreProblemProblemId;
        this.categoryIds = categoryIds;
        this.difficulty = difficulty;
    }

    public static Problem create(Id coreProlemProblemId, List<Id> categoryIds, Difficulty difficulty) {
        return new Problem(
            Id.generateRandom(),
            0,
            coreProlemProblemId,
            categoryIds,
            difficulty);
    }

    public Id getCoreProblemProblemId() {
        return coreProblemProblemId;
    }

    public List<Id> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Id> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCoreProblemId(Id coreProblemProblemId) {
        this.coreProblemProblemId = coreProblemProblemId;
    }
    
}

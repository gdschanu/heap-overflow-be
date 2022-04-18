package hanu.gdsc.practiceProblem.domains;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Problem extends IdentitifedVersioningDomainObject {
    private Id coreProblemId;
    private List<Id> categoryIds;
    private Difficulty difficulty;


    public Problem(Id id, long version, Id coreProblemId, List<Id> categoryIds,
                   Difficulty difficulty) {
        super(id, version);
        this.coreProblemId = coreProblemId;
        this.categoryIds = categoryIds;
        this.difficulty = difficulty;
    }

    public static Problem create(Id coreProlemId, List<Id> categoryIds, Difficulty difficulty) {
        return new Problem(
            Id.generateRandom(),
            0,
            coreProlemId,
            categoryIds,
            difficulty);
    }

    public Id getCoreProblemId() {
        return coreProblemId;
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

    public void setCoreProblemId(Id coreProblemId) {
        this.coreProblemId = coreProblemId;
    }
    
}

package hanu.gdsc.practiceProblem.domains;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Problem extends IdentitifedVersioningDomainObject {
    private Id coreProlemId;
    private long likeCount;
    private long dislikeCount;
    private List<Id> categoryIds;
    private Difficulty difficulty;


    public Problem(Id id, long version, Id coreProlemId, long likeCount, long dislikeCount, List<Id> categoryIds,
            Difficulty difficulty) {
        super(id, version);
        this.coreProlemId = coreProlemId;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.categoryIds = categoryIds;
        this.difficulty = difficulty;
    }

    public static Problem create(Id coreProlemId, List<Id> categoryIds, Difficulty difficulty) {
        return new Problem(
            Id.generateRandom(),
            0,
            coreProlemId,
            0,
            0,
            categoryIds,
            difficulty);
    }

    public Id getCoreProlemId() {
        return coreProlemId;
    }

    public void setCoreProlemId(Id coreProlemId) {
        this.coreProlemId = coreProlemId;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public long getDislikeCount() {
        return dislikeCount;
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
    
}

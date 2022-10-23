package hanu.gdsc.domain.practiceProblem_problemDiscussion.models;

import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentifiedDomainObject;

public class Post extends IdentifiedDomainObject {
    private Id problemId;
    private Id corePostId;
    private DateTime createdAt;

    private Post(Id id, Id problemId, Id corePostId, DateTime createdAt) {
        super(id);
        this.problemId = problemId;
        this.corePostId = corePostId;
        this.createdAt = createdAt;
    }

    public static Post create(Id problemId, Id corePostId) {
        return new Post(
                Id.generateRandom(),
                problemId,
                corePostId,
                DateTime.now()
        );
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public Id getProblemId() {
        return problemId;
    }

    public Id getCorePostId() {
        return corePostId;
    }
}

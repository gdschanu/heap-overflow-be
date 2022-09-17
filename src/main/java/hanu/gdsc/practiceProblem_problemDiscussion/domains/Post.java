package hanu.gdsc.practiceProblem_problemDiscussion.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

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

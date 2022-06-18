package hanu.gdsc.practiceProblem_problemCategory.domains;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;

public class Post extends IdentifiedDomainObject {
    private Id problemId;
    private Id corePostId;

    private Post(Id id, Id problemId, Id corePostId) {
        super(id);
        this.problemId = problemId;
        this.corePostId = corePostId;
    }

    public static Post create(Id problemId, Id corePostId) {
        return new Post(
                Id.generateRandom(),
                problemId,
                corePostId
        );
    }
}

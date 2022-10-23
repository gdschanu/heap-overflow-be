package hanu.gdsc.domain.core_discussion.models;

import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;

public class Comment extends IdentitifedVersioningDomainObject {
    private String content;
    private Id author;
    private DateTime createdAt;
    private DateTime updatedAt;
    private Id parentCommentId;
    private Id postId;
    private String serviceToCreate;

    private Comment(Id id,
                    long version,
                    String content,
                    Id author,
                    DateTime createdAt,
                    DateTime updatedAt,
                    Id parentCommentId,
                    Id postId,
                    String serviceToCreate) {
        super(id, version);
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.parentCommentId = parentCommentId;
        this.postId = postId;
        this.serviceToCreate = serviceToCreate;
    }

    public Comment create(String content,
                          Id author,
                          Id parentCommentId,
                          Id postId,
                          String serviceToCreate) {
        return new Comment(
                Id.generateRandom(),
                0,
                content,
                author,
                DateTime.now(),
                DateTime.now(),
                parentCommentId,
                postId,
                serviceToCreate
        );
    }

    public String getContent() {
        return content;
    }

    public Id getAuthor() {
        return author;
    }


    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public Id getParentCommentId() {
        return parentCommentId;
    }

    public Id getPostId() {
        return postId;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}

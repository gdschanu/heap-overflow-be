package hanu.gdsc.core_discussion.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;

public class Post extends IdentitifedVersioningDomainObject {
    private String title;
    private Id author;
    private DateTime createdAt;
    private DateTime updatedAt;
    private String content;
    private String serviceToCreate;

    private Post(Id id,
                 long version,
                 String title,
                 Id author,
                 DateTime createdAt,
                 DateTime updatedAt,
                 String content,
                 String serviceToCreate) {
        super(id, version);
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.serviceToCreate = serviceToCreate;
    }

    public static Post create(String title,
                              Id author,
                              String content,
                              String serviceToCreate) {
        return new Post(Id.generateRandom(),
                0,
                title,
                author,
                DateTime.now(),
                DateTime.now(),
                content,
                serviceToCreate);
    }

    public String getTitle() {
        return title;
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

    public String getContent() {
        return content;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}

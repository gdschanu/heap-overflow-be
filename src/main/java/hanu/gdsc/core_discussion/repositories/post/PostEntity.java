package hanu.gdsc.core_discussion.repositories.post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_discussion_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private long version;
    private String title;
    private String author;
    private long createdAt;
    private long updatedAt;
    private String content;
    private String serviceToCreate;

    public Post toDomain() {
        try {
            Constructor<Post> constructor = Post.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    Long.TYPE,
                    hanu.gdsc.share.domains.Id.class,
                    DateTime.class,
                    DateTime.class,
                    String.class,
                    String.class
            );
            constructor.setAccessible(true);
            Post post = constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(id),
                    version,
                    new hanu.gdsc.share.domains.Id(author),
                    DateTime.fromMillis(createdAt),
                    DateTime.fromMillis(updatedAt),
                    content,
                    serviceToCreate
            );
            return post;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PostEntity toEntity(Post post) {
        return new PostEntity(
                post.getId().toString(),
                post.getVersion(),
                post.getTitle(),
                post.getAuthor().toString(),
                post.getCreatedAt().toMillis(),
                post.getUpdatedAt().toMillis(),
                post.getContent(),
                post.getServiceToCreate()
        );
    }
}

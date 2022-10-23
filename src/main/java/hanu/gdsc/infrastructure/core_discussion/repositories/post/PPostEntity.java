package hanu.gdsc.infrastructure.core_discussion.repositories.post;

import hanu.gdsc.domain.core_discussion.models.Post;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_discussion_post")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PPostEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private Long version;
    @Column(columnDefinition = "VARCHAR(255)")
    private String title;
    private String authorId;
    @Column(columnDefinition = "VARCHAR(255)")
    private String createdAt;
    @Column(columnDefinition = "VARCHAR(255)")
    private String updatedAt;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String serviceToCreate;

    public static PPostEntity toEntity(Post post) {
        return PPostEntity.builder()
                .id(post.getId().toString())
                .version(post.getVersion())
                .title(post.getTitle())
                .authorId(post.getAuthor().toString())
                .createdAt(post.getCreatedAt().toString())
                .updatedAt(post.getUpdatedAt().toString())
                .content(post.getContent())
                .serviceToCreate(post.getServiceToCreate())
                .build();
    }

    public static Post toDomain(PPostEntity postEntity) {
        try {
            Constructor<Post> constructor = Post.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    Long.TYPE,
                    String.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    DateTime.class,
                    DateTime.class,
                    String.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
              new hanu.gdsc.domain.share.models.Id(postEntity.getId()),
              postEntity.getVersion(),
              postEntity.getTitle(),
              new hanu.gdsc.domain.share.models.Id(postEntity.getAuthorId()),
              new DateTime(postEntity.getCreatedAt()),
              new DateTime(postEntity.getUpdatedAt()),
              postEntity.getContent(),
              postEntity.getServiceToCreate()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

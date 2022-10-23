package hanu.gdsc.infrastructure.core_discussion.repositories.comment;

import hanu.gdsc.domain.core_discussion.models.Comment;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_discussion_comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private Long version;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String authorId;
    @Column(columnDefinition = "VARCHAR(255)")
    private String createdAt;
    @Column(columnDefinition = "VARCHAR(255)")
    private String updatedAt;
    @Column(columnDefinition = "VARCHAR(30)")
    private String parentCommentId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String postId;
    private String serviceToCreate;

    public static Comment toDomain(CommentEntity entity) {
        try {
            Constructor<Comment> constructor = Comment.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    Long.TYPE,
                    String.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    DateTime.class,
                    DateTime.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    String.class
                    );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(entity.getId()),
                    entity.getVersion(),
                    entity.getContent(),
                    new hanu.gdsc.domain.share.models.Id(entity.getAuthorId()),
                    new DateTime(entity.getCreatedAt()),
                    new DateTime(entity.getUpdatedAt()),
                    new hanu.gdsc.domain.share.models.Id(entity.getParentCommentId()),
                    new hanu.gdsc.domain.share.models.Id(entity.getPostId()),
                    entity.getServiceToCreate()
            );
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

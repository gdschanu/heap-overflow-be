package hanu.gdsc.core_discussion.repositories.comment;

import hanu.gdsc.core_discussion.domains.Comment;
import hanu.gdsc.share.domains.DateTime;
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
                    hanu.gdsc.share.domains.Id.class,
                    Long.TYPE,
                    String.class,
                    hanu.gdsc.share.domains.Id.class,
                    DateTime.class,
                    DateTime.class,
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    String.class
                    );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(entity.getId()),
                    entity.getVersion(),
                    entity.getContent(),
                    new hanu.gdsc.share.domains.Id(entity.getAuthorId()),
                    new DateTime(entity.getCreatedAt()),
                    new DateTime(entity.getUpdatedAt()),
                    new hanu.gdsc.share.domains.Id(entity.getParentCommentId()),
                    new hanu.gdsc.share.domains.Id(entity.getPostId()),
                    entity.getServiceToCreate()
            );
        } catch(Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

package hanu.gdsc.infrastructure.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.domain.practiceProblem_problemDiscussion.models.Post;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "practice_problem_post")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PPPostEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String problemId;
    private String corePostId;
    private String createdAt;
    private long createdAtMillis;

    public static PPPostEntity toEntity(Post post) {
        return PPPostEntity.builder()
                .id(post.getId().toString())
                .problemId(post.getProblemId().toString())
                .corePostId(post.getCorePostId().toString())
                .createdAt(post.getCreatedAt().toString())
                .createdAtMillis(post.getCreatedAt().toMillis())
                .build();
    }

    public static Post toDomain(PPPostEntity postEntity) {
        try {
            Constructor<Post> constructor = Post.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    DateTime.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(postEntity.getId()),
                    new hanu.gdsc.domain.share.models.Id(postEntity.getProblemId()),
                    new hanu.gdsc.domain.share.models.Id(postEntity.getCorePostId()),
                    new DateTime(postEntity.getCreatedAt())

            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

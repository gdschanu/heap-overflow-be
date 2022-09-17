package hanu.gdsc.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import lombok.*;

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
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(postEntity.getId()),
                    new hanu.gdsc.share.domains.Id(postEntity.getProblemId()),
                    new hanu.gdsc.share.domains.Id(postEntity.getCorePostId())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

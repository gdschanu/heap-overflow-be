package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.dislikeCount;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.DislikeCount;
import lombok.*;

import java.lang.reflect.Constructor;

import javax.persistence.*;

@Entity
@Table(name = "practice_problem_dislike_count")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DislikeCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private long dislikeCount;
    @Version
    private long version;

    public DislikeCount toDomain() {
        try {
            Constructor<DislikeCount> constructor = DislikeCount.class.getDeclaredConstructor(
                Long.TYPE,
                hanu.gdsc.share.domains.Id.class,
                Long.TYPE
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    version,
                    new hanu.gdsc.share.domains.Id(problemId),
                    dislikeCount
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static DislikeCountEntity fromDomain(DislikeCount dislikeCount) {
        return DislikeCountEntity.builder()
                .problemId(dislikeCount.getProblemId().toString())
                .dislikeCount(dislikeCount.getDislikeCount())
                .version(dislikeCount.getVersion())
                .build();
    }
}

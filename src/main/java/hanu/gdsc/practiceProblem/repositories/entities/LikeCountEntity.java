package hanu.gdsc.practiceProblem.repositories.entities;
import hanu.gdsc.practiceProblem.domains.LikeCount;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "practice_problem_like_count")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private long likeCount;
    @Version
    private long version;

    public LikeCount toDomain() {
        return new LikeCount(
                version,
                new hanu.gdsc.share.domains.Id(problemId),
                likeCount
        );
    }

    public static LikeCountEntity fromDomain(LikeCount cnt) {
        return LikeCountEntity.builder()
                .problemId(cnt.getProblemId().toString())
                .likeCount(cnt.getLikeCount())
                .version(cnt.getVersion())
                .build();
    }
}

package hanu.gdsc.practiceProblem.repositories.entities;

import hanu.gdsc.practiceProblem.domains.DislikeCount;
import lombok.*;

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
        return new DislikeCount(
                version,
                new hanu.gdsc.share.domains.Id(problemId),
                dislikeCount
        );
    }

    public static DislikeCountEntity fromDomain(DislikeCount dislikeCount) {
        return DislikeCountEntity.builder()
                .problemId(dislikeCount.getProblemId().toString())
                .dislikeCount(dislikeCount.getDislikeCount())
                .version(dislikeCount.getVersion())
                .build();
    }
}

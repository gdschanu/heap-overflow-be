package hanu.gdsc.practiceProblem.repositories.entities;

import java.util.UUID;

import javax.persistence.*;

import hanu.gdsc.practiceProblem.domains.Problem;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "practice_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PracticeProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coreProblemId;
    private long likeCount;
    private long dislikeCount;
    @ManyToOne()
    @JoinColumn(name = "category_id", columnDefinition = "VARCHAR(30)")
    private CategoryEntity category;

    public static PracticeProblemEntity toEntity(Problem problem) {
        return PracticeProblemEntity.builder()
            .id(problem.getId().toString())
            .version(problem.getVersion())
            .coreProblemId(problem.getCoreProlemId().toString())
            .likeCount(problem.getLikeCount())
            .dislikeCount(problem.getDislikeCount())
            .category(CategoryEntity.toEntity(problem.getCategory()))
            .build();
    }

    public static Problem toDomain(PracticeProblemEntity practiceProblemEntity) {
        return new Problem(
            new hanu.gdsc.share.domains.Id(practiceProblemEntity.getId()),
            practiceProblemEntity.getVersion(),
            new hanu.gdsc.share.domains.Id(practiceProblemEntity.getCoreProblemId()),
            practiceProblemEntity.getLikeCount(),
            practiceProblemEntity.getDislikeCount(),
            CategoryEntity.toDomain(practiceProblemEntity.getCategory())
        );
    }
}

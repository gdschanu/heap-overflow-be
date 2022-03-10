package hanu.gdsc.practiceProblem.repositories.entities;

import java.util.UUID;

import javax.persistence.*;

import hanu.gdsc.practiceProblem.domains.Problem;
import lombok.*;
@Entity
@Table(name = "practice_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PracticeProblemEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    private UUID coreProblemId;
    private long likeCount;
    private long dislikeCount;
    @ManyToOne()
    @JoinColumn(name = "category_uuid")
    private CategoryEntity category;

    public static PracticeProblemEntity toEntity(Problem problem) {
        return PracticeProblemEntity.builder()
            .id(problem.getId().toUUID())
            .version(problem.getVersion())
            .coreProblemId(problem.getCoreProlemId().toUUID())
            .likeCount(problem.getLikeCount())
            .dislikeCount(problem.getDislikeCount())
            .category(CategoryEntity.toEntity(problem.getCategory()))
            .build();
    }
}

package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Problem;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "contest_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContestProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private long version;
    private int ordinal;
    private String coreProblemId;
    private int score;
    @ManyToOne
    @JoinColumn(name = "contest_id", columnDefinition = "VARCHAR(30)")
    private ContestEntity contest;

    public static ContestProblemEntity fromDomain(Problem problem, ContestEntity contestEntity) {
        return ContestProblemEntity.builder()
                .id(contestEntity.getId().toString() + "#" + problem.getOrdinal())
                .version(problem.getVersion())
                .ordinal(problem.getOrdinal())
                .coreProblemId(problem.getCoreProblemId().toString())
                .score(problem.getScore())
                .contest(contestEntity)
                .build();
    }

    public Problem toDomain() {
        return new Problem(
                version,
                ordinal,
                new hanu.gdsc.share.domains.Id(coreProblemId),
                score
        );
    }
}

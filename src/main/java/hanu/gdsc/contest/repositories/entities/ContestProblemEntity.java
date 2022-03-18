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

    public static ContestProblemEntity fromDomain(Problem problem) {
        return ContestProblemEntity.builder()
                .id(problem.getId().toString())
                .version(problem.getVersion())
                .ordinal(problem.getOrdinal())
                .coreProblemId(problem.getCoreProblemId().toString())
                .score(problem.getScore())
                .build();
    }

    public Problem toDomain() {
        return new Problem(
                new hanu.gdsc.share.domains.Id(id),
                version,
                ordinal,
                new hanu.gdsc.share.domains.Id(coreProblemId),
                score
        );
    }
}

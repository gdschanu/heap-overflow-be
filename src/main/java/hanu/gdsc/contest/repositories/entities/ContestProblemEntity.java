package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Problem;
import lombok.*;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private long version;
    private int ordinal;
    @Column(columnDefinition = "BINARY(16)")
    private UUID coreProblemId;
    private int score;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contest;

    public static ContestProblemEntity fromDomain(Problem problem) {
        return ContestProblemEntity.builder()
                .id(problem.getId().toUUID())
                .version(problem.getVersion())
                .ordinal(problem.getOrdinal())
                .coreProblemId(problem.getCoreProblemId().toUUID())
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

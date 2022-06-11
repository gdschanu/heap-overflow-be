package hanu.gdsc.contest.repositories.contest;

import hanu.gdsc.contest.domains.Problem;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;


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
        try {
            Constructor<Problem> con = Problem.class.getDeclaredConstructor(
                    Long.TYPE,
                    Integer.TYPE,
                    hanu.gdsc.share.domains.Id.class,
                    Integer.TYPE
            );
            con.setAccessible(true);
            return con.newInstance(
                    version,
                    ordinal,
                    new hanu.gdsc.share.domains.Id(coreProblemId),
                    score
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

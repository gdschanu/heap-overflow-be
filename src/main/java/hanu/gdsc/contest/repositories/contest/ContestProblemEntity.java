package hanu.gdsc.contest.repositories.contest;

import hanu.gdsc.contest.domains.ContestProblem;
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

    public static ContestProblemEntity fromDomain(ContestProblem contestProblem, ContestEntity contestEntity) {
        return ContestProblemEntity.builder()
                .id(contestEntity.getId().toString() + "#" + contestProblem.getOrdinal())
                .version(contestProblem.getVersion())
                .ordinal(contestProblem.getOrdinal())
                .coreProblemId(contestProblem.getCoreProblemId().toString())
                .score(contestProblem.getScore())
                .contest(contestEntity)
                .build();
    }

    public ContestProblem toDomain() {
        try {
            Constructor<ContestProblem> con = ContestProblem.class.getDeclaredConstructor(
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

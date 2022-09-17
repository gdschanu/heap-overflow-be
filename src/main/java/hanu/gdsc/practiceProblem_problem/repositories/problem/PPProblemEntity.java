package hanu.gdsc.practiceProblem_problem.repositories.problem;

import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.time.Instant;

@Entity
@Table(name = "practice_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coreProblemProblemId;
    private String difficulty;
    @Column(columnDefinition = "DATETIME")
    private Instant createdAt;
    private long createdAtMillis;

    public static PPProblemEntity toEntity(Problem problem) {
        return PPProblemEntity.builder()
                .id(problem.getId().toString())
                .version(problem.getVersion())
                .coreProblemProblemId(problem.getCoreProblemProblemId().toString())
                .difficulty(problem.getDifficulty().toString())
                .createdAt(Instant.parse(problem.getCreatedAt().toString()))
                .createdAtMillis(problem.getCreatedAt().toMillis())
            .build();
    }

    public static Problem toDomain(PPProblemEntity PPProblemEntity) {
        try {
            Constructor<Problem> constructor = Problem.class.getDeclaredConstructor(
                hanu.gdsc.share.domains.Id.class,
                Long.TYPE,
                hanu.gdsc.share.domains.Id.class,
                Difficulty.class,
                DateTime.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(PPProblemEntity.getId()),
                PPProblemEntity.getVersion(),
                new hanu.gdsc.share.domains.Id(PPProblemEntity.getCoreProblemProblemId()),
                Difficulty.valueOf(PPProblemEntity.getDifficulty()),
                new DateTime(PPProblemEntity.getCreatedAt().toString())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

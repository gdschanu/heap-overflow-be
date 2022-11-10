package hanu.gdsc.infrastructure.practiceProblem_problem.repositories.problem;

import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

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
    @Column(columnDefinition = "VARCHAR(100)")
    private String createdAt;
    private long createdAtMillis;
    private int point;

    public static PPProblemEntity toEntity(Problem problem) {
        return PPProblemEntity.builder()
                .id(problem.getId().toString())
                .version(problem.getVersion())
                .coreProblemProblemId(problem.getCoreProblemProblemId().toString())
                .difficulty(problem.getDifficulty().toString())
                .createdAt(problem.getCreatedAt().toString())
                .createdAtMillis(problem.getCreatedAt().toMillis())
                .point(problem.getPoint())
            .build();
    }

    public static Problem toDomain(PPProblemEntity PPProblemEntity) {
        try {
            Constructor<Problem> constructor = Problem.class.getDeclaredConstructor(
                hanu.gdsc.domain.share.models.Id.class,
                Long.TYPE,
                hanu.gdsc.domain.share.models.Id.class,
                Difficulty.class,
                DateTime.class,
                Integer.TYPE
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                new hanu.gdsc.domain.share.models.Id(PPProblemEntity.getId()),
                PPProblemEntity.getVersion(),
                new hanu.gdsc.domain.share.models.Id(PPProblemEntity.getCoreProblemProblemId()),
                Difficulty.valueOf(PPProblemEntity.getDifficulty()),
                new DateTime(PPProblemEntity.getCreatedAt().toString()),
                PPProblemEntity.getPoint()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

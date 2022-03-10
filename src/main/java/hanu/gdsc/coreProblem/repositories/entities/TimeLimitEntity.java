package hanu.gdsc.coreProblem.repositories.entities;

import java.util.UUID;

import javax.persistence.*;

import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import lombok.*;

@Entity
@Table(name="core_problem_time_limit")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TimeLimitEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_uuid")
    private ProblemEntity problem;
    private String programmingLanguage;
    private long timeLimit;
    @Version
    @Column(name="version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;

    public static TimeLimitEntity toEntity(TimeLimit timeLimitDomain) {
        return TimeLimitEntity.builder()
                .id(timeLimitDomain.getId().toUUID())
                .version(timeLimitDomain.getVersion())
                .programmingLanguage(timeLimitDomain.getProgrammingLanguage().toString())
                .timeLimit(timeLimitDomain.getTimeLimit().getValue())
                .build();
    }

    public static TimeLimit toDomain(TimeLimitEntity timeLimitEntity) {
        return new TimeLimit(
            new hanu.gdsc.share.domains.Id(timeLimitEntity.getId()),
            timeLimitEntity.getVersion(),
            ProgrammingLanguage.valueOf(timeLimitEntity.getProgrammingLanguage()),
            new Millisecond(timeLimitEntity.getTimeLimit())
        );
    }
}

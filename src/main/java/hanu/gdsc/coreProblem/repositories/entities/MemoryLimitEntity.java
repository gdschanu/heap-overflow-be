package hanu.gdsc.coreProblem.repositories.entities;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import lombok.*;

@Entity
@Table(name="core_problem_memory_limit")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MemoryLimitEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_id", columnDefinition = "VARCHAR(30)")
    private ProblemEntity problem;
    private String programmingLanguage;
    private double memoryLimit;

    public static MemoryLimitEntity toEntity(MemoryLimit memoryLimitDomain) {
        return MemoryLimitEntity.builder()
                .id(memoryLimitDomain.getId().toString())
                .programmingLanguage(memoryLimitDomain.getProgrammingLanguage().toString())
                .memoryLimit(memoryLimitDomain.getMemoryLimit().getValue())
                .build();
    }

    public static MemoryLimit toDomain(MemoryLimitEntity memoryLimitEntity) {
        try {
            Constructor<MemoryLimit> constructor = MemoryLimit.class.getDeclaredConstructor(
                hanu.gdsc.share.domains.Id.class,
                ProgrammingLanguage.class,
                KB.class
            );
            return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(memoryLimitEntity.getId()),
                ProgrammingLanguage.valueOf(memoryLimitEntity.getProgrammingLanguage()),
                new KB(memoryLimitEntity.getMemoryLimit())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
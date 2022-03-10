package hanu.gdsc.coreProblem.repositories.entities;


import java.util.UUID;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_uuid")
    private ProblemEntity problem;
    private String programmingLanguage;
    private long memoryLimit;
    @Column(name="version", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private long version;

    public static MemoryLimitEntity toEntity(MemoryLimit memoryLimitDomain) {
        return MemoryLimitEntity.builder()
                .id(memoryLimitDomain.getId().toUUID())
                .version(memoryLimitDomain.getVersion())
                .programmingLanguage(memoryLimitDomain.getProgrammingLanguage().toString())
                .memoryLimit(memoryLimitDomain.getMemoryLimit().getValue())
                .build();
    }

    public static MemoryLimit toDomain(MemoryLimitEntity memoryLimitEntity) {
        return new MemoryLimit(
            new hanu.gdsc.share.domains.Id(memoryLimitEntity.getId()),
            memoryLimitEntity.getVersion(),
            ProgrammingLanguage.valueOf(memoryLimitEntity.getProgrammingLanguage()),
            new KB(memoryLimitEntity.getMemoryLimit())
        );
    }
}
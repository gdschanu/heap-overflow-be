package hanu.gdsc.coreProblem.repositories.entities;


import java.util.UUID;

import javax.persistence.*;

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
    @ManyToOne()
    @JoinColumn(name="problem_uuid")
    private ProblemEntity problem;
    private String programmingLanguage;
    private long memoryLimit;
    @Column(name="version")
    @Version
    private Long version;
}
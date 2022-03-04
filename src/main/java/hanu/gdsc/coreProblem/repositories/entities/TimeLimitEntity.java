package hanu.gdsc.coreProblem.repositories.entities;

import java.util.UUID;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name="problem_uuid")
    private ProblemEntity problem;
    private String programmingLanguage;
    private long timeLimit;
    @Column(name="version")
    @Version
    private long version;
}

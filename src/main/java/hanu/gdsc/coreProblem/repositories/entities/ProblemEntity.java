package hanu.gdsc.coreProblem.repositories.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "core_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String description;
    private String difficulty;
    private Long ACsCount;
    @OneToMany(mappedBy="problem")
    private List<TestCaseEntity> testCases = new ArrayList<>();
    @OneToMany(mappedBy="problem")
    private List<TimeLimitEntity> timeLimits = new ArrayList<>();
    @OneToMany(mappedBy = "problem")
    private List<MemoryLimitEntity> memoryLimits = new ArrayList<>();
    @ElementCollection(targetClass = String.class)
    private List<String> allowedProgrammingLanguages = new ArrayList<>();
    @Column(name = "version")
    @Version
    private Long version;
}

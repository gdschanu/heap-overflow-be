package hanu.gdsc.problem.repositories.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "problem")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProblemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String description;
    private String difficulty;
    @OneToMany(mappedBy="problem")
    private List<TestCaseEntity> testCases = new ArrayList<>();
    @OneToMany(mappedBy="problem")
    private List<TimeLimitEntity> timeLimits = new ArrayList<>();
    @OneToMany(mappedBy="problem")
    private List<MemoryLimitEntity> memoryLimits = new ArrayList<>();
    private List<String> allowedProgrammingLanguages = new ArrayList<>();
    private String accessibility;
    @Column(name="version")
    @Version
    private int version;
}

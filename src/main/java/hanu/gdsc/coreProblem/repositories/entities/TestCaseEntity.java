package hanu.gdsc.coreProblem.repositories.entities;

import lombok.*;

import javax.persistence.*;

import hanu.gdsc.coreProblem.domains.TestCase;

import java.util.UUID;

@Entity
@Table(name = "core_problem_test_case")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="problem_uuid")
    private ProblemEntity problem;
    @Version
    private long version;
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;

    public static TestCaseEntity toEntity(TestCase testCase) {
        return TestCaseEntity.builder()
            .id(testCase.getId().toUUID())
            .version(testCase.getVersion())
            .input(testCase.getInput())
            .expectedOutput(testCase.getExpectedOutput())
            .ordinal(testCase.getOrdinal())
            .isSample(testCase.isSample())
            .description(testCase.getDescription())
            .build();
    }

    public static TestCase toDomain(TestCaseEntity testCaseEntity) {
        return new TestCase(
            new hanu.gdsc.share.domains.Id(testCaseEntity.getId()),
            testCaseEntity.getVersion(),
            testCaseEntity.getInput(),
            testCaseEntity.getExpectedOutput(),
            testCaseEntity.getOrdinal(),
            testCaseEntity.isSample(),
            testCaseEntity.getDescription()
        );
    }
}

package hanu.gdsc.coreProblem.repositories.entities;

import lombok.*;

import javax.persistence.*;

import hanu.gdsc.coreProblem.domains.TestCase;

@Entity
@Table(name = "core_problem_test_case")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_id", columnDefinition = "VARCHAR(30)")
    private ProblemEntity problem;
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;

    public static TestCaseEntity toEntity(TestCase testCase) {
        return TestCaseEntity.builder()
            .id(testCase.getId().toString())
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
            testCaseEntity.getInput(),
            testCaseEntity.getExpectedOutput(),
            testCaseEntity.getOrdinal(),
            testCaseEntity.isSample(),
            testCaseEntity.getDescription()
        );
    }
}

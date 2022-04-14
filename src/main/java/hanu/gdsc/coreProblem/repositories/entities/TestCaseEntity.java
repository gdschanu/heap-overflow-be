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
    @Version
    private long version;
    private String problemId;
    private String input;
    private String expectedOutput;
    private int ordinal;
    private boolean isSample;
    private String description;
    private String serviceToCreate;

    public static TestCaseEntity toEntity(TestCase testCase) {
        return TestCaseEntity.builder()
            .id(testCase.getId().toString())
            .version(testCase.getVersion())
            .problemId(testCase.getProblemId().toString())
            .input(testCase.getInput())
            .expectedOutput(testCase.getExpectedOutput())
            .ordinal(testCase.getOrdinal())
            .isSample(testCase.isSample())
            .description(testCase.getDescription())
            .serviceToCreate(testCase.getServiceToCreate())
            .build();
    }

    public static TestCase toDomain(TestCaseEntity testCaseEntity) {
        return new TestCase(
            new hanu.gdsc.share.domains.Id(testCaseEntity.getId()),
            testCaseEntity.getVersion(),
            new hanu.gdsc.share.domains.Id(testCaseEntity.getProblemId()),
            testCaseEntity.getInput(),
            testCaseEntity.getExpectedOutput(),
            testCaseEntity.getOrdinal(),
            testCaseEntity.isSample(),
            testCaseEntity.getDescription(),
            testCaseEntity.getServiceToCreate()
        );
    }
}

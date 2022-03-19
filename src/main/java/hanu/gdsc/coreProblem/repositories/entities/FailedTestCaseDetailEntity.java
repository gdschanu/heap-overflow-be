package hanu.gdsc.coreProblem.repositories.entities;

import hanu.gdsc.coreProblem.domains.FailedTestCaseDetail;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "core_problem_failed_test_case_detail")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FailedTestCaseDetailEntity {
    @Column(columnDefinition = "VARCHAR(30)")
    @Id
    private String id;
    private int failedAtLine;
    private String input;
    private String actualOutput;
    private String expectedOutput;
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submission_id", referencedColumnName = "id", columnDefinition = "VARCHAR(30)")
    private SubmissionEntity submission;

    public FailedTestCaseDetail toDomain() {
        return new FailedTestCaseDetail(
                new hanu.gdsc.share.domains.Id(id),
                failedAtLine,
                input,
                actualOutput,
                expectedOutput,
                description
        );
    }

    public static FailedTestCaseDetailEntity fromDomain(FailedTestCaseDetail domain) {
        return FailedTestCaseDetailEntity.builder()
                .id(domain.getId().toString())
                .failedAtLine(domain.getFailedAtLine())
                .input(domain.getInput())
                .actualOutput(domain.getActualOutput())
                .expectedOutput(domain.getExpectedOutput())
                .description(domain.getDescription())
                .build();
    }
}

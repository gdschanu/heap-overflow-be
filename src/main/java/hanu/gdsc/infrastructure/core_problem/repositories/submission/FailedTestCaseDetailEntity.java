package hanu.gdsc.infrastructure.core_problem.repositories.submission;

import hanu.gdsc.domain.core_problem.models.FailedTestCaseDetail;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

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
    @Column(columnDefinition = "LONGTEXT")
    private String input;
    @Column(columnDefinition = "LONGTEXT")
    private String actualOutput;
    @Column(columnDefinition = "LONGTEXT")
    private String expectedOutput;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @OneToOne(mappedBy = "failedTestCaseDetail")
    private SubmissionEntity submission;


    public FailedTestCaseDetail toDomain() {
        try {
        Constructor<FailedTestCaseDetail> constructor = FailedTestCaseDetail.class.getDeclaredConstructor(
            hanu.gdsc.domain.share.models.Id.class,
            Integer.TYPE,
            String.class,
            String.class, 
            String.class,
            String.class
        );
        constructor.setAccessible(true);
        return constructor.newInstance(
                new hanu.gdsc.domain.share.models.Id(id),
                failedAtLine,
                input,
                actualOutput,
                expectedOutput,
                description
        );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static FailedTestCaseDetailEntity fromDomain(FailedTestCaseDetail domain, SubmissionEntity submission) {
        return FailedTestCaseDetailEntity.builder()
                .id(domain.getId().toString())
                .failedAtLine(domain.getFailedAtLine())
                .input(domain.getInput())
                .actualOutput(domain.getActualOutput())
                .expectedOutput(domain.getExpectedOutput())
                .description(domain.getDescription())
                .submission(submission)
                .build();
    }
}

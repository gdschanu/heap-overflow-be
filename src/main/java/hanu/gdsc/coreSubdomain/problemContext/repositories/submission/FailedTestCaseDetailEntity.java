package hanu.gdsc.coreSubdomain.problemContext.repositories.submission;

import hanu.gdsc.coreSubdomain.problemContext.domains.FailedTestCaseDetail;
import lombok.*;

import java.lang.reflect.Constructor;

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
    @OneToOne(mappedBy = "failedTestCaseDetail")
    private SubmissionEntity submission;


    public FailedTestCaseDetail toDomain() {
        try {
        Constructor<FailedTestCaseDetail> constructor = FailedTestCaseDetail.class.getDeclaredConstructor(
            hanu.gdsc.share.domains.Id.class,
            Integer.TYPE,
            String.class,
            String.class, 
            String.class,
            String.class
        );
        constructor.setAccessible(true);
        return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(id),
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

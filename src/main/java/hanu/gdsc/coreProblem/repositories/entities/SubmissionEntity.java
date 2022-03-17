package hanu.gdsc.coreProblem.repositories.entities;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "core_problem_submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private String programmingLanguage;
    private long runTimeInMillis;
    private long memoryInKB;
    private String submittedAtInZonedDateTime;
    private String code;
    private String status;
    @OneToOne
    @JoinColumn(name = "failed_test_case_id", referencedColumnName = "id", columnDefinition = "VARCHAR(30)")
    private TestCaseEntity failedTestCases;

    public static SubmissionEntity toEntity(Submission submission) {
        return SubmissionEntity.builder()
                .id(submission.getId().toString())
                .version(submission.getVersion())
                .problemId(submission.getProblemId().toString())
                .programmingLanguage(submission.getProgrammingLanguage().toString())
                .runTimeInMillis(submission.getRunTime().getValue())
                .memoryInKB(submission.getMemory().getValue())
                .submittedAtInZonedDateTime(submission.getSubmittedAt().toZonedDateTime().toString())
                .code(submission.getCode())
                .status(submission.getStatus().toString())
                .failedTestCases(TestCaseEntity.toEntity(submission.getFailedTestCase()))
                .build();
    }

    public static Submission toDomain(SubmissionEntity submissionEntity) {
        return new Submission(
                new hanu.gdsc.share.domains.Id(submissionEntity.getId()),
                submissionEntity.getVersion(),
                new hanu.gdsc.share.domains.Id(submissionEntity.getProblemId()),
                ProgrammingLanguage.valueOf(submissionEntity.getProgrammingLanguage()),
                new Millisecond(submissionEntity.getRunTimeInMillis()),
                new KB(submissionEntity.getMemoryInKB()),
                new DateTime(submissionEntity.getSubmittedAtInZonedDateTime()),
                submissionEntity.getCode(),
                Status.valueOf(submissionEntity.getStatus()),
                TestCaseEntity.toDomain(submissionEntity.getFailedTestCases())
        );
    }
}

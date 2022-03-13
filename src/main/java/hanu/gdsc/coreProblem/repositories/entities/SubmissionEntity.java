package hanu.gdsc.coreProblem.repositories.entities;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    private long version;
    @Column(columnDefinition = "BINARY(16)")
    private UUID problemId;
    private String programmingLanguage;
    private long runTimeInMillis;
    private long memoryInKB;
    private String submittedAtInZonedDateTime;
    private String code;
    private String status;
    @OneToOne
    @JoinColumn(name = "failed_test_case_id", referencedColumnName = "id")
    private TestCaseEntity failedTestCases;

    public static SubmissionEntity toEntity(Submission submission) {
        return SubmissionEntity.builder()
                .id(submission.getId().toUUID())
                .version(submission.getVersion())
                .problemId(submission.getProblemId().toUUID())
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

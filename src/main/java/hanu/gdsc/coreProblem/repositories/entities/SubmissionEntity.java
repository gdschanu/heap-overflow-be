package hanu.gdsc.coreProblem.repositories.entities;

import lombok.*;
import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.share.domains.DateTime;

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
    @Column(columnDefinition = "BINARY(16)")
    private TestCase failedTestCases;

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
                    .failedTestCases(submission.getFailedTestCase())
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
            submissionEntity.getFailedTestCases()
        );
    }
}

package hanu.gdsc.coreProblem.repositories.entities;

import lombok.*;

import hanu.gdsc.coreProblem.domains.Submission;

import javax.persistence.*;

import java.util.List;
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
    private double memoryInKB;
    private String submittedAtInZonedDateTime;
    private String code;
    private String status;
    @Column(columnDefinition = "BINARY(16)")
    private UUID failedTestCasesId;

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
                    .failedTestCasesId(submission.getFailedTestCase().getId().toUUID())
                    .build();
    }
}

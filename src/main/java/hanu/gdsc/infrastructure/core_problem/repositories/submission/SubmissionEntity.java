package hanu.gdsc.infrastructure.core_problem.repositories.submission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.models.*;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;

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
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private String programmingLanguage;
    private Long runTimeInMillis;
    private Double memoryInKB;
    private String submittedAtInZonedDateTime;
    @Column(columnDefinition = "LONGTEXT")
    private String code;
    private String status;
    @Column(columnDefinition = "LONGTEXT")
    private String failedTestCaseDetail;
    private String serviceToCreate;
    @Column(columnDefinition = "LONGTEXT")
    private String message;
    private long submittedAtMillis;


    public static SubmissionEntity toEntity(Submission submission, ObjectMapper objectMapper) {
        try {
            SubmissionEntity e = SubmissionEntity.builder()
                    .id(submission.getId().toString())
                    .problemId(submission.getProblemId().toString())
                    .programmingLanguage(submission.getProgrammingLanguage().toString())
                    .runTimeInMillis(submission.getRunTime() == null ? null : submission.getRunTime().getValue())
                    .memoryInKB(submission.getMemory() == null ? null : submission.getMemory().getValue())
                    .submittedAtInZonedDateTime(submission.getSubmittedAt().toZonedDateTime().toString())
                    .code(submission.getCode())
                    .status(submission.getStatus().toString())
                    .serviceToCreate(submission.getServiceToCreate())
                    .coderId(submission.getCoderId().toString())
                    .message(submission.getMessage())
                    .submittedAtMillis(submission.getSubmittedAt().toMillis())
                    .failedTestCaseDetail(submission.getFailedTestCaseDetail() == null ?
                            null :
                            objectMapper.writeValueAsString(submission.getFailedTestCaseDetail()))
                    .build();
            return e;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static Submission toDomain(SubmissionEntity submissionEntity, ObjectMapper objectMapper) {
        try {
            Constructor<Submission> constructor = Submission.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    ProgrammingLanguage.class,
                    Millisecond.class,
                    KB.class,
                    DateTime.class,
                    String.class,
                    Status.class,
                    FailedTestCaseDetail.class,
                    String.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(submissionEntity.getId()),
                    new hanu.gdsc.domain.share.models.Id(submissionEntity.getProblemId()),
                    ProgrammingLanguage.valueOf(submissionEntity.getProgrammingLanguage()),
                    submissionEntity.getRunTimeInMillis() == null ? null : new Millisecond(submissionEntity.getRunTimeInMillis()),
                    submissionEntity.getMemoryInKB() == null ? null : new KB(submissionEntity.getMemoryInKB()),
                    new DateTime(submissionEntity.getSubmittedAtInZonedDateTime()),
                    submissionEntity.getCode(),
                    Status.valueOf(submissionEntity.getStatus()),
                    submissionEntity.getFailedTestCaseDetail() == null ?
                            null : objectMapper.readValue(submissionEntity.getFailedTestCaseDetail(), FailedTestCaseDetail.class),
                    submissionEntity.getServiceToCreate(),
                    new hanu.gdsc.domain.share.models.Id(submissionEntity.coderId),
                    submissionEntity.message
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

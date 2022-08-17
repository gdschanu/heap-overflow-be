package hanu.gdsc.core_problem.repositories.submission;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "failed_test_case_detail_id", referencedColumnName = "id", columnDefinition = "VARCHAR(30)")
    private FailedTestCaseDetailEntity failedTestCaseDetail;
    private String serviceToCreate;
    private String message;

    public static SubmissionEntity toEntity(Submission submission) {
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
                .build();
        e.setFailedTestCaseDetail(submission.getFailedTestCaseDetail() == null ? null :
                FailedTestCaseDetailEntity.fromDomain(submission.getFailedTestCaseDetail(), e));
        return e;
    }

    public static Submission toDomain(SubmissionEntity submissionEntity) {
        try {
            Constructor<Submission> constructor = Submission.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    ProgrammingLanguage.class,
                    Millisecond.class,
                    KB.class,
                    DateTime.class,
                    String.class,
                    Status.class,
                    FailedTestCaseDetail.class,
                    String.class,
                    hanu.gdsc.share.domains.Id.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(submissionEntity.getId()),
                    new hanu.gdsc.share.domains.Id(submissionEntity.getProblemId()),
                    ProgrammingLanguage.valueOf(submissionEntity.getProgrammingLanguage()),
                    submissionEntity.getRunTimeInMillis() == null ? null : new Millisecond(submissionEntity.getRunTimeInMillis()),
                    submissionEntity.getMemoryInKB() == null ? null :  new KB(submissionEntity.getMemoryInKB()),
                    new DateTime(submissionEntity.getSubmittedAtInZonedDateTime()),
                    submissionEntity.getCode(),
                    Status.valueOf(submissionEntity.getStatus()),
                    submissionEntity.getFailedTestCaseDetail() == null ?
                            null : submissionEntity.getFailedTestCaseDetail().toDomain(),
                    submissionEntity.getServiceToCreate(),
                    new hanu.gdsc.share.domains.Id(submissionEntity.coderId),
                    submissionEntity.message
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

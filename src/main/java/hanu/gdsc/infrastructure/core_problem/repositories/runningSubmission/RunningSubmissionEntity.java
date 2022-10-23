package hanu.gdsc.infrastructure.core_problem.repositories.runningSubmission;

import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_problem_running_submission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RunningSubmissionEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private String serviceToCreate;
    @Column(columnDefinition = "LONGTEXT")
    private String code;
    private String programmingLanguage;
    private long submittedAt;
    private String submittedAtString;
    private int judgingTestCase;
    private int totalTestCases;

    private int locked;
    private long lockedUntil;
    @Version
    private long version;

    public RunningSubmission toDomain() {
        try {
            Constructor<RunningSubmission> constructor = RunningSubmission.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    Long.TYPE,
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    String.class,
                    String.class,
                    ProgrammingLanguage.class,
                    DateTime.class,
                    Integer.TYPE,
                    Integer.TYPE
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(id),
                    version,
                    new hanu.gdsc.domain.share.models.Id(coderId),
                    new hanu.gdsc.domain.share.models.Id(problemId),
                    serviceToCreate,
                    code,
                    ProgrammingLanguage.from(programmingLanguage),
                    new DateTime(submittedAtString),
                    judgingTestCase,
                    totalTestCases
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static RunningSubmissionEntity fromDomain(RunningSubmission runningSubmission,
                                                     int locked,
                                                     long lockedUntil) {
        return RunningSubmissionEntity.builder()
                .id(runningSubmission.getId().toString())
                .coderId(runningSubmission.getCoderId().toString())
                .problemId(runningSubmission.getProblemId().toString())
                .serviceToCreate(runningSubmission.getServiceToCreate())
                .code(runningSubmission.getCode())
                .programmingLanguage(runningSubmission.getProgrammingLanguage().toString())
                .submittedAt(runningSubmission.getSubmittedAt().toMillis())
                .submittedAtString(runningSubmission.getSubmittedAt().toString())
                .judgingTestCase(runningSubmission.getJudgingTestCase())
                .totalTestCases(runningSubmission.getTotalTestCases())
                .locked(locked)
                .lockedUntil(lockedUntil)
                .version(runningSubmission.getVersion())
                .build();
    }
}

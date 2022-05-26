package hanu.gdsc.coreProblem.repositories.entities;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.RunningSubmission;
import hanu.gdsc.share.domains.DateTime;
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
    private String code;
    private String programmingLanguage;
    private long submittedAt;
    private String submittedAtString;

    private int locked;
    private long lockedUntil;
    @Version
    private int version;

    public RunningSubmission toDomain() {
        try {
            Constructor<RunningSubmission> constructor = RunningSubmission.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    String.class,
                    String.class,
                    ProgrammingLanguage.class,
                    DateTime.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(id),
                    new hanu.gdsc.share.domains.Id(coderId),
                    new hanu.gdsc.share.domains.Id(problemId),
                    serviceToCreate,
                    code,
                    ProgrammingLanguage.from(programmingLanguage),
                    new DateTime(submittedAtString)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public static RunningSubmissionEntity fromDomain(RunningSubmission runningSubmission,
                                                     int locked,
                                                     long lockedUntil,
                                                     int version) {
        return RunningSubmissionEntity.builder()
                .id(runningSubmission.getId().toString())
                .coderId(runningSubmission.getCoderId().toString())
                .problemId(runningSubmission.getProblemId().toString())
                .serviceToCreate(runningSubmission.getServiceToCreate())
                .code(runningSubmission.getCode())
                .programmingLanguage(runningSubmission.getProgrammingLanguage().toString())
                .submittedAt(runningSubmission.getSubmittedAt().toMillis())
                .submittedAtString(runningSubmission.getSubmittedAt().toString())
                .locked(locked)
                .lockedUntil(lockedUntil)
                .version(version)
                .build();
    }
}

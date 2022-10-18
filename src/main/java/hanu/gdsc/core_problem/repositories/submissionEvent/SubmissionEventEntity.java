package hanu.gdsc.core_problem.repositories.submissionEvent;

import hanu.gdsc.core_problem.domains.Status;
import hanu.gdsc.core_problem.domains.SubmissionEvent;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_problem_submission_event")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SubmissionEventEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private String status;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;

    public static SubmissionEventEntity toEntity(SubmissionEvent submissionEvent) {
        return SubmissionEventEntity.builder()
                .id(submissionEvent.getId().toString())
                .problemId(submissionEvent.getProblemId().toString())
                .status(submissionEvent.getStatus().toString())
                .coderId(submissionEvent.getCoderId().toString())
                .build();
    }

    public static SubmissionEvent toDomain(SubmissionEventEntity submissionEventEntity) {
        if (submissionEventEntity == null) {
            return null;
        }
        try {
            Constructor<SubmissionEvent> constructor = SubmissionEvent.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    Status.class,
                    hanu.gdsc.share.domains.Id.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(submissionEventEntity.getId()),
                    new hanu.gdsc.share.domains.Id(submissionEventEntity.getProblemId()),
                    Status.valueOf(submissionEventEntity.getStatus()),
                    new hanu.gdsc.share.domains.Id(submissionEventEntity.getCoderId())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

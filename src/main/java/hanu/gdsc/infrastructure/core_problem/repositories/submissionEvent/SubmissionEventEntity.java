package hanu.gdsc.infrastructure.core_problem.repositories.submissionEvent;

import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
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
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    Status.class,
                    hanu.gdsc.domain.share.models.Id.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(submissionEventEntity.getId()),
                    new hanu.gdsc.domain.share.models.Id(submissionEventEntity.getProblemId()),
                    Status.valueOf(submissionEventEntity.getStatus()),
                    new hanu.gdsc.domain.share.models.Id(submissionEventEntity.getCoderId())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

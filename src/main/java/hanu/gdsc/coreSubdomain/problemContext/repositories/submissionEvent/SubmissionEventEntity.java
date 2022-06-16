package hanu.gdsc.coreSubdomain.problemContext.repositories.submissionEvent;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.coreSubdomain.problemContext.domains.Status;
import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionEvent;
import lombok.*;

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

    public static SubmissionEventEntity toEntity(SubmissionEvent submissionEvent) {
        return SubmissionEventEntity.builder()
                .id(submissionEvent.getId().toString())
                .problemId(submissionEvent.getProblemId().toString())
                .status(submissionEvent.getStatus().toString())
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
                Status.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(submissionEventEntity.getId()),
                new hanu.gdsc.share.domains.Id(submissionEventEntity.getProblemId()),
                Status.valueOf(submissionEventEntity.getStatus())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

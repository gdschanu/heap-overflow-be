package hanu.gdsc.coreProblem.repositories.entities;

import javax.persistence.*;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.SubmissionEvent;
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
            return new SubmissionEvent(
                new hanu.gdsc.share.domains.Id(submissionEventEntity.getId()),
                new hanu.gdsc.share.domains.Id(submissionEventEntity.getProblemId()),
                Status.valueOf(submissionEventEntity.getStatus())
            );
    }
}

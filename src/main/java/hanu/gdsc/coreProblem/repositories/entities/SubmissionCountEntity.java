package hanu.gdsc.coreProblem.repositories.entities;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "core_problem_submission_count")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class SubmissionCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    @Version
    private Long version;
    private long ACsCount;
    private long submissionsCount;
    private String serviceToCreate;

    public static SubmissionCountEntity toEntity(SubmissionCount submissionCount) {
        return SubmissionCountEntity.builder()
                .problemId(submissionCount.getProblemId().toString())
                .ACsCount(submissionCount.getACsCount())
                .submissionsCount(submissionCount.getSubmissionsCount())
                .serviceToCreate(submissionCount.getServiceToCreate())
                .build();
    }

    public static SubmissionCount toDomain(SubmissionCountEntity submissionCountEntity) {
        return new SubmissionCount(
                submissionCountEntity.getVersion(),
                new hanu.gdsc.share.domains.Id(submissionCountEntity.getProblemId()),
                submissionCountEntity.getACsCount(),
                submissionCountEntity.getSubmissionsCount(),
                submissionCountEntity.serviceToCreate
        );
    }
}

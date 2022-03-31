package hanu.gdsc.coreProblem.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import lombok.*;

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
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    @Version
    private Long version;
    private int ACsCount;
    private int submissionsCount;

    public static SubmissionCountEntity toEntity(SubmissionCount submissionCount) {
        return SubmissionCountEntity.builder()
                .id(submissionCount.getId().toString())
                .problemId(submissionCount.getProblemId().toString())
                .ACsCount(submissionCount.getACsCount())
                .submissionsCount(submissionCount.getSubmissionsCount())
                .build();
    }

    public static SubmissionCount toDomain(SubmissionCountEntity submissionCountEntity) {
        return new SubmissionCount(
            new hanu.gdsc.share.domains.Id(submissionCountEntity.getId()),
            submissionCountEntity.getVersion(),
            new hanu.gdsc.share.domains.Id(submissionCountEntity.getProblemId()),
            submissionCountEntity.getACsCount(),
            submissionCountEntity.getSubmissionsCount()
        );
    }
}

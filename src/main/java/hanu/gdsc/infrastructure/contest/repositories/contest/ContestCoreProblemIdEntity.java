package hanu.gdsc.infrastructure.contest.repositories.contest;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contest_core_problem_id")
@Getter
@NoArgsConstructor
public class ContestCoreProblemIdEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(60)")
    private String id;
    @Column(columnDefinition = "VARCHAR(60)")
    private String contestId;
    @Column(columnDefinition = "VARCHAR(60)")
    private String coreProblemId;

    public ContestCoreProblemIdEntity(String contestId, String coreProblemId) {
        id = contestId + "#" + coreProblemId;
        this.contestId = contestId;
        this.coreProblemId = coreProblemId;
    }
}

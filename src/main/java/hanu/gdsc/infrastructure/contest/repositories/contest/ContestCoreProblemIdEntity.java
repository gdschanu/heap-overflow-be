package hanu.gdsc.infrastructure.contest.repositories.contest;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contest_core_problem_id")
@Getter
public class ContestCoreProblemIdEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(60)")
    private String id;
    private String contestId;
    private String coreProblemId;

    public ContestCoreProblemIdEntity(String contestId, String coreProblemId) {
        id = contestId + "#" + coreProblemId;
        this.contestId = contestId;
        this.coreProblemId = coreProblemId;
    }

    public ContestCoreProblemIdEntity(String id, String contestId, String coreProblemId) {
        this.id = id;
        this.contestId = contestId;
        this.coreProblemId = coreProblemId;
    }
}

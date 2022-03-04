package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.ProblemScore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "contest_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemScoreEntity {
    private UUID id;
    private long version;
    private UUID problemId;
    private int score;
    private int tryCount;

    public static ProblemScoreEntity fromDomains(ProblemScore problemScore) {
        return ProblemScoreEntity.builder()
                .id(problemScore.getId().toUUID())
                .version(problemScore.getVersion())
                .problemId(problemScore.getProblemId().toUUID())
                .score(problemScore.getScore())
                .tryCount(problemScore.getTryCount())
                .build();
    }
}

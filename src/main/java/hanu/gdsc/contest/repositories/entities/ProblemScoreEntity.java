package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.ProblemScore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contest_problem_score")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemScoreEntity {
    @Id
    private UUID id;
    private long version;
    private UUID problemId;
    private int score;
    private int tryCount;
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;

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

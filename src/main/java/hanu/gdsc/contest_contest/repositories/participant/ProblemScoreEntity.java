package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.ProblemScore;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "contest_problem_score")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemScoreEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private int score;
    private int tryCount;
    @ManyToOne
    @JoinColumn(name = "participant_id", columnDefinition = "VARCHAR(30)")
    private ParticipantEntity participant;

    public static ProblemScoreEntity fromDomains(ProblemScore problemScore) {
        return ProblemScoreEntity.builder()
                .id(problemScore.getId().toString())
                .version(problemScore.getVersion())
                .problemId(problemScore.getProblemId().toString())
                .score(problemScore.getScore())
                .tryCount(problemScore.getTryCount())
                .build();
    }
    public ProblemScore toDomain() {
        try {
            Constructor<ProblemScore> con = ProblemScore.class.getDeclaredConstructor();
            con.setAccessible(true);
            return con.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

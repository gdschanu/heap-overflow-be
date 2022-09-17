package hanu.gdsc.contest_contest.repositories.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "contest_participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(60)")
    private String id;
    @Version
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String contestId;
    private int participantRank; // "rank" trùng với từ khóa của SQL
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private List<ProblemScoreEntity> problemScores;
    private String createdAt;
    private long createdAtMillis;

    public static ParticipantEntity fromDomains(Participant participant) {
        return ParticipantEntity.builder()
                .id(participant.getCoderId() + "#" + participant.getContestId())
                .version(participant.getVersion())
                .coderId(participant.getCoderId().toString())
                .contestId(participant.getContestId().toString())
                .participantRank(participant.getRank())
                .problemScores(participant.getProblemScores().stream()
                        .map(x -> ProblemScoreEntity.fromDomains(x))
                        .collect(Collectors.toList()))
                .createdAt(participant.getCreatedAt().toString())
                .createdAtMillis(participant.getCreatedAt().toMillis())
                .build();
    }

    public Participant toDomain() {
        try {
            Constructor<Participant> con = Participant.class.getDeclaredConstructor(
                    long.class,
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    int.class,
                    List.class,
                    DateTime.class
            );
            con.setAccessible(true);
            return con.newInstance(
                    version,
                    new hanu.gdsc.share.domains.Id(coderId),
                    new hanu.gdsc.share.domains.Id(contestId),
                    participantRank,
                    problemScores.stream()
                            .map(x -> x.toDomain())
                            .collect(Collectors.toList()),
                    new DateTime(createdAt)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

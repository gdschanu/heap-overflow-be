package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Participant;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
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
    @Column(columnDefinition = "VARCHAR(30)")
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

    public static ParticipantEntity fromDomains(Participant participant) {
        return ParticipantEntity.builder()
                .id(participant.getId().toString())
                .version(participant.getVersion())
                .coderId(participant.getCoderId().toString())
                .participantRank(participant.getRank())
                .problemScores(participant.getProblemScores().stream()
                        .map(x -> ProblemScoreEntity.fromDomains(x))
                        .collect(Collectors.toList()))
                .build();
    }
}

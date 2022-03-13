package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Participant;
import lombok.*;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    private long version;
    @Column(columnDefinition = "BINARY(16)")
    private UUID coderId;
    @Column(columnDefinition = "BINARY(16)")
    private UUID contestId;
    private int participantRank; // "rank'
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private List<ProblemScoreEntity> problemScores;

    public static ParticipantEntity fromDomains(Participant participant) {
        return ParticipantEntity.builder()
                .id(participant.getId().toUUID())
                .version(participant.getVersion())
                .coderId(participant.getCoderId().toUUID())
                .participantRank(participant.getRank())
                .problemScores(participant.getProblemScores().stream()
                        .map(x -> ProblemScoreEntity.fromDomains(x))
                        .collect(Collectors.toList()))
                .build();
    }
}

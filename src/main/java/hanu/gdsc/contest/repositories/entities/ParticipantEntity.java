package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Participant;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "contest_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private long version;
    private UUID coderId;
    private UUID contestId;
    private int rank;
    private List<ProblemScoreEntity> problemScores;

    public static ParticipantEntity fromDomains(Participant participant) {
        return ParticipantEntity.builder()
                .id(participant.getId().toUUID())
                .version(participant.getVersion())
                .coderId(participant.getCoderId().toUUID())
                .rank(participant.getRank())
                .problemScores(participant.getProblemScores().stream()
                        .map(x -> ProblemScoreEntity.fromDomains(x))
                        .collect(Collectors.toList()))
                .build();
    }
}

package hanu.gdsc.infrastructure.contest.repositories.participant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.models.ProblemScore;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

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
    private String problemScores;
    private String createdAt;
    private long createdAtMillis;

    public static ParticipantEntity fromDomains(Participant participant, ObjectMapper objectMapper) {
        try {
            return ParticipantEntity.builder()
                    .id(participant.getCoderId() + "#" + participant.getContestId())
                    .version(participant.getVersion())
                    .coderId(participant.getCoderId().toString())
                    .contestId(participant.getContestId().toString())
                    .participantRank(participant.getRank())
                    .problemScores(objectMapper.writeValueAsString(participant.getProblemScores()))
                    .createdAt(participant.getCreatedAt().toString())
                    .createdAtMillis(participant.getCreatedAt().toMillis())
                    .build();
        } catch (JsonProcessingException e) {
            throw new Error(e);
        }
    }

    public Participant toDomain(ObjectMapper objectMapper) {
        try {
            Constructor<Participant> con = Participant.class.getDeclaredConstructor(
                    long.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    int.class,
                    List.class,
                    DateTime.class
            );
            con.setAccessible(true);
            final List<ProblemScore> justAVariableForGettingClass = new ArrayList<>();
            return con.newInstance(
                    version,
                    new hanu.gdsc.domain.share.models.Id(coderId),
                    new hanu.gdsc.domain.share.models.Id(contestId),
                    participantRank,
                    objectMapper.readValue(problemScores, justAVariableForGettingClass.getClass()),
                    new DateTime(createdAt)
            );
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

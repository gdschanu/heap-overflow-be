package hanu.gdsc.contest.repositories.participantCount;

import hanu.gdsc.contest.domains.ParticipantCount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "contest_participant_count")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String contestId;

    private long num;

    public static ParticipantCountEntity toEntity(ParticipantCount participantCount) {
        return ParticipantCountEntity.builder()
                .contestId(participantCount.getId().toString())
                .num(participantCount.getNum()).build();
    }

    public ParticipantCount toDomain() {
       try{
           Constructor<ParticipantCount> con = ParticipantCount.class.getDeclaredConstructor(
                   hanu.gdsc.share.domains.Id.class,
                   long.class
           );
           con.setAccessible(true);
           return con.newInstance(
                 new hanu.gdsc.share.domains.Id(contestId),
                   num
           );
       } catch (Exception e) {
           e.printStackTrace();
           throw new Error(e);
       }
    }
}

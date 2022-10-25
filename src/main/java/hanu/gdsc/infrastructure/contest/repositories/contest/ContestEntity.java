package hanu.gdsc.infrastructure.contest.repositories.contest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contest_contest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContestEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private long version;
    private String name;
    private String description;
    private String startAt;
    private String endAt;
    @Column(columnDefinition = "VARCHAR(30)")
    private String createdBy;
    @Column(columnDefinition = "LONGTEXT")
    private String problems;
    private String createdAt;
    private long createdAtMillis;

    public static ContestEntity fromDomain(Contest contest, ObjectMapper objectMapper) {
        try {
            ContestEntity res = ContestEntity.builder()
                    .id(contest.getId().toString())
                    .version(contest.getVersion())
                    .name(contest.getName())
                    .description(contest.getDescription())
                    .startAt(contest.getStartAt().toString())
                    .endAt(contest.getEndAt().toString())
                    .createdBy(contest.getCreatedBy().toString())
                    .createdAt(contest.getCreatedAt().toString())
                    .createdAtMillis(contest.getCreatedAt().toMillis())
                    .build();
            res.setProblems(objectMapper.writeValueAsString(contest.getProblems()));
            return res;
        } catch (JsonProcessingException e) {
            throw new Error(e);
        }
    }

    public Contest toDomain(ObjectMapper objectMapper) {
        try {
            Constructor<Contest> con = Contest.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    Long.TYPE,
                    String.class,
                    String.class,
                    DateTime.class,
                    DateTime.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    List.class,
                    DateTime.class
            );
            con.setAccessible(true);
            /*
            Just for getting class of generic type
             */
            return con.newInstance(
                    new hanu.gdsc.domain.share.models.Id(id),
                    version,
                    name,
                    description,
                    new DateTime(startAt),
                    new DateTime(endAt),
                    new hanu.gdsc.domain.share.models.Id(createdBy),
                    objectMapper.readValue(problems, new TypeReference<List<ContestProblem>>() {}),
                    new DateTime(createdAt)
            );
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}

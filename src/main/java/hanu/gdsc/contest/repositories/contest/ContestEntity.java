package hanu.gdsc.contest.repositories.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<ContestProblemEntity> problems;

    public static ContestEntity fromDomain(Contest contest) {
        ContestEntity res = ContestEntity.builder()
                .id(contest.getId().toString())
                .version(contest.getVersion())
                .name(contest.getName())
                .description(contest.getDescription())
                .startAt(contest.getStartAt().toString())
                .endAt(contest.getEndAt().toString())
                .createdBy(contest.getCreatedBy().toString())
                .build();
        res.setProblems(contest.getProblems().stream()
                .map(x -> ContestProblemEntity.fromDomain(x, res))
                .collect(Collectors.toList()));
        return res;
    }

    public Contest toDomain() {
        try {
            Constructor<Contest> con = Contest.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    Long.TYPE,
                    String.class,
                    String.class,
                    DateTime.class,
                    DateTime.class,
                    hanu.gdsc.share.domains.Id.class,
                    List.class
            );
            con.setAccessible(true);
            return con.newInstance(
                    new hanu.gdsc.share.domains.Id(id),
                    version,
                    name,
                    description,
                    new DateTime(startAt),
                    new DateTime(endAt),
                    new hanu.gdsc.share.domains.Id(createdBy),
                    problems.stream().map(x -> x.toDomain()).collect(Collectors.toList())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

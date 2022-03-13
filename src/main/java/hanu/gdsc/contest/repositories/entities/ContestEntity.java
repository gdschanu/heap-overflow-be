package hanu.gdsc.contest.repositories.entities;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.share.domains.DateTime;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    private long version;
    private String name;
    private String description;
    private String startAt;
    private String endAt;
    private UUID createdBy;
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<ContestProblemEntity> problems;

    public static ContestEntity fromDomain(Contest contest) {
        return ContestEntity.builder()
                .id(contest.getId().toUUID())
                .version(contest.getVersion())
                .name(contest.getName())
                .description(contest.getDescription())
                .startAt(contest.getStartAt().toString())
                .endAt(contest.getEndAt().toString())
                .createdBy(contest.getCreatedBy().toUUID())
                .problems(contest.getProblems().stream()
                        .map(x -> ContestProblemEntity.fromDomain(x))
                        .collect(Collectors.toList()))
                .build();
    }

    public Contest toDomain() {
        return new Contest(
                new hanu.gdsc.share.domains.Id(id),
                version,
                name,
                description,
                new DateTime(startAt),
                new DateTime(endAt),
                new hanu.gdsc.share.domains.Id(createdBy),
                problems.stream().map(x -> x.toDomain()).collect(Collectors.toList())
        );
    }
}

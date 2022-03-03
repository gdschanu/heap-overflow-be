package hanu.gdsc.contest.repositories.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    private long version;
    private String name;
    private String description;
    private String startAt;
    private String endAt;
    private UUID createdBy;
    @OneToMany(mappedBy = "contest_contest")
    private List<ProblemEntity> problems;
}

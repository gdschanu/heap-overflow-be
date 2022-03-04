package hanu.gdsc.contest.repositories.entities;

import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "contest_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContestProblemEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private long version;
    private int ordinal;
    @Column(columnDefinition = "BINARY(16)")
    private UUID coreProblemId;
    private int score;
    @ManyToOne
    @JoinColumn(name = "contest_uuid")
    private ContestEntity contest;
}

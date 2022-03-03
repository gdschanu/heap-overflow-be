package hanu.gdsc.contest.repositories.entities;

import java.util.UUID;
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
public class ProblemEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private long version;
    private int ordinal;
    @Column(columnDefinition = "BINARY(16)")
    private UUID coreProblemId;
    private int score;
}

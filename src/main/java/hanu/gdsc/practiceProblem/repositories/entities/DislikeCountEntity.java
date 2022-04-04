package hanu.gdsc.practiceProblem.repositories.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "practice_problem_dislike_count")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DislikeCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private long dislikeCount;
    @Version
    private long version;
}

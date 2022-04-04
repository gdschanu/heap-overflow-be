package hanu.gdsc.practiceProblem.repositories.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "practice_problem_like_count")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCountEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private long likeCount;
    @Version
    private long version;
}

package hanu.gdsc.coreProblem.repositories.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "core_problem_event")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String problemId;
    private String status;
}

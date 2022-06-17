package hanu.gdsc.practiceProblem_problem.repositories.problem;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.category.CategoryEntity;
import lombok.*;

@Entity
@Table(name = "practice_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PPProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coreProblemProblemId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "practice_problem_problem_category", joinColumns = @JoinColumn(name="practice_problem_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<CategoryEntity> category = new HashSet<>();
    private String difficulty;

    public static PPProblemEntity toEntity(Problem problem) {
        return PPProblemEntity.builder()
            .id(problem.getId().toString())
            .version(problem.getVersion())
            .coreProblemProblemId(problem.getCoreProblemProblemId().toString())
            .difficulty(problem.getDifficulty().toString())
            .build();
    }

    public static Problem toDomain(PPProblemEntity PPProblemEntity) {
        List<hanu.gdsc.share.domains.Id> categoryIds = new ArrayList<>();
        for(CategoryEntity category : PPProblemEntity.getCategory()) {
            categoryIds.add(new hanu.gdsc.share.domains.Id(category.getId()));
        }
        try {
            Constructor<Problem> constructor = Problem.class.getDeclaredConstructor(
                hanu.gdsc.share.domains.Id.class,
                Long.TYPE,
                hanu.gdsc.share.domains.Id.class,
                List.class,
                Difficulty.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(PPProblemEntity.getId()),
                PPProblemEntity.getVersion(),
                new hanu.gdsc.share.domains.Id(PPProblemEntity.getCoreProblemProblemId()),
                categoryIds,
                Difficulty.valueOf(PPProblemEntity.getDifficulty())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
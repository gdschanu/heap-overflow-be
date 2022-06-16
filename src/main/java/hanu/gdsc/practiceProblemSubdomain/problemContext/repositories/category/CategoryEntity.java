package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.category;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Category;
import hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.problem.ProblemEntity;
import lombok.*;

@Entity
@Table(name = "practice_problem_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    @Column(name = "version")
    private long version;
    private String name;
    @ManyToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProblemEntity> practiceProblem = new HashSet<>();
    
    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                    .id(category.getId().toString() )
                    .version(category.getVersion())
                    .name(category.getName())
                    .build();
    }

    public static Category toDomain(CategoryEntity categoryEntity) {
        try {
            Constructor<Category> constructor = Category.class.getDeclaredConstructor(
                hanu.gdsc.share.domains.Id.class,
                Long.TYPE,
                String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                new hanu.gdsc.share.domains.Id(categoryEntity.getId()),
                categoryEntity.getVersion(),
                categoryEntity.getName()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}

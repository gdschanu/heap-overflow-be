package hanu.gdsc.practiceProblem.repositories.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import hanu.gdsc.practiceProblem.domains.Category;
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
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    private String name;
    @ManyToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PracticeProblemEntity> practiceProblem = new HashSet<>();
    
    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                    .id(category.getId().toString() )
                    .version(category.getVersion())
                    .name(category.getName())
                    .build();
    }

    public static Category toDomain(CategoryEntity categoryEntity) {
        return new Category(
            new hanu.gdsc.share.domains.Id(categoryEntity.getId()),
            categoryEntity.getVersion(),
            categoryEntity.getName()
        );
    }
}

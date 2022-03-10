package hanu.gdsc.practiceProblem.repositories.entities;

import java.util.List;
import java.util.UUID;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<PracticeProblemEntity> practiceProblems;

    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                    .id(category.getId().toUUID())
                    .version(category.getVersion())
                    .name(category.getName())
                    .build();
    }
}

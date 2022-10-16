package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.exceptions.InvalidInputException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String name;
    private String serviceToCreate;

    public static CategoryEntity fromDomains(Category category) {
        return CategoryEntity.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .serviceToCreate(category.getServiceToCreate())
                .build();
    }

    public Category toDomain(){
        try {
            return new Category(
                    new hanu.gdsc.share.domains.Id(id),
                    name,
                    serviceToCreate
            );
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }

}

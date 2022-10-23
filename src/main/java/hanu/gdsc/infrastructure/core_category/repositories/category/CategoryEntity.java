package hanu.gdsc.infrastructure.core_category.repositories.category;

import hanu.gdsc.domain.core_category.models.Category;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;

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

    public static Category toDomain(CategoryEntity categoryEntity) {
        try {
            Constructor<Category> constructor = Category.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    String.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.domain.share.models.Id(categoryEntity.getId()),
                    categoryEntity.getName(),
                    categoryEntity.getServiceToCreate()
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

}

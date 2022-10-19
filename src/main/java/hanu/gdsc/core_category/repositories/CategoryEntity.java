package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.exceptions.InvalidInputException;
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
                    hanu.gdsc.share.domains.Id.class,
                    String.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(categoryEntity.getId()),
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

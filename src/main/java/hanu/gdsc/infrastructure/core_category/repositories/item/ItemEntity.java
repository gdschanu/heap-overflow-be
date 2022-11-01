package hanu.gdsc.infrastructure.core_category.repositories.item;


import hanu.gdsc.domain.core_category.models.Item;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;
import java.util.List;

@Entity
@Table(name = "item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ItemEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String serviceToCreate;

    public static ItemEntity fromDomains(Item item) {
        return ItemEntity.builder()
                .id(item.getId().toString())
                .serviceToCreate(item.getServiceToCreate())
                .build();
    }

    public static Item toDomain(ItemEntity itemEntity) {
        try {
            Constructor<Item> constructor = Item.class.getDeclaredConstructor(
                    hanu.gdsc.domain.share.models.Id.class,
                    String.class
            );
            constructor.setAccessible(true);
            return constructor.newInstance(new hanu.gdsc.domain.share.models.Id(itemEntity.getId()), itemEntity.getServiceToCreate());
        }

        catch (Exception e) {
            e.printStackTrace();
            throw new Error (e);
        }
    }

}

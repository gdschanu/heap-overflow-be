package hanu.gdsc.infrastructure.core_category.repositories.item;

import hanu.gdsc.domain.core_category.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {

}

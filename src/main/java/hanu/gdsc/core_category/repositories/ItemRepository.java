package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.domains.Item;
import hanu.gdsc.share.domains.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository {

    public Item save(Category[] categoryIds);

    public Item findById(Id id);

    public void delete(Item item);

}

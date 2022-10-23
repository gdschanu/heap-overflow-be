package hanu.gdsc.domain.core_category.repositories;

import hanu.gdsc.domain.core_category.models.Item;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    public Item save(List<Id> categoryIds);

    public Item findById(Id id);

    public void delete(Item item);

}

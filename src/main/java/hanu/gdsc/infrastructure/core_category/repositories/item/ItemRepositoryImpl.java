package hanu.gdsc.infrastructure.core_category.repositories.item;

import hanu.gdsc.domain.core_category.models.Item;
import hanu.gdsc.domain.core_category.repositories.ItemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public Item save(List<Id> categoryIds) {
        return null;
    }

    @Override
    public Item findById(Id id) {
        return null;
    }

    @Override
    public void delete(Item item) {

    }
}

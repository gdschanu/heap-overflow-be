package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.domains.Item;
import hanu.gdsc.share.domains.Id;
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

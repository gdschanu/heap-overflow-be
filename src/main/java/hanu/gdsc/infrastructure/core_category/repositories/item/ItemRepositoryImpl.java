package hanu.gdsc.infrastructure.core_category.repositories.item;

import hanu.gdsc.domain.core_category.models.Item;
import hanu.gdsc.domain.core_category.repositories.ItemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {


    @Autowired
    private ItemJpaRepository itemJpaRepository;

    @Override
    public Item saveItem(Item item) {
        itemJpaRepository.save(ItemEntity.fromDomains(item));
        return item;
    }
/*
    @Override
    public Item save(List<Id> categoryIds) {
        return null;
    }
*/

    @Override
    public Item findById(Id id) {
        ItemEntity item = itemJpaRepository.getById(id.toString());
        return ItemEntity.toDomain(item);
    }

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        ItemEntity item = itemJpaRepository.getById(id.toString());
        itemJpaRepository.delete(item);
    }
}

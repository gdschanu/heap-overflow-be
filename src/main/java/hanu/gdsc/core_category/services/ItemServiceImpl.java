package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.domains.Item;
import hanu.gdsc.core_category.repositories.ItemRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class ItemServiceImpl {

    @Autowired
    private ItemRepository itemRepository;

    public void createItem(Category[] categoryIds) {
        itemRepository.save(categoryIds);
    }

    public void deleteItem(Id id) {
        Item item = itemRepository.findById(id);
        itemRepository.delete(item);

    }
}

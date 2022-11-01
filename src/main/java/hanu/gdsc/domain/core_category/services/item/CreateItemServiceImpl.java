package hanu.gdsc.domain.core_category.services.item;


import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.core_category.models.Item;
import hanu.gdsc.domain.core_category.repositories.CategoryRepository;
import hanu.gdsc.domain.core_category.repositories.ItemRepository;
import hanu.gdsc.domain.core_like.exceptions.InvalidActionException;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateItemServiceImpl implements CreateItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void createItem(List<Id> categoryIds) throws InvalidInputException {
        List<Id> filtered = new ArrayList<>();
        for (int i = 0; i < categoryIds.size(); i++) {
            if (itemRepository.findById(categoryIds.get(i)).getId() == (categoryIds.get(i))) {
                filtered.add(categoryIds.get(i));
            }
        }
        Item item = Item.create(filtered, "");
        itemRepository.saveItem(item);
    }
}

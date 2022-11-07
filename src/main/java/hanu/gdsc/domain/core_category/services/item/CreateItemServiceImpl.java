package hanu.gdsc.domain.core_category.services.item;


import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.core_category.models.Item;
import hanu.gdsc.domain.core_category.repositories.CategoryRepository;
import hanu.gdsc.domain.core_category.repositories.ItemRepository;
import hanu.gdsc.domain.core_like.exceptions.InvalidActionException;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
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
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Override
    public void createItem(List<Id> categoryIds, String serviceToCreate) throws NotFoundException, InvalidInputException {
        List<Id> filter = new ArrayList<>();
        for (Id id : categoryIds) {
            if (categoryRepository.findById(id).getId() == id) {
                filter.add(id);
            }
            else {
                throw new NotFoundException("Id not exists!");
            }
        }
        Item saveItem = Item.create(filter, serviceToCreate);
        itemRepository.saveItem(saveItem);
    }
}

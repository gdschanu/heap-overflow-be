package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class ItemServiceImpl {

    @Autowired
    private ItemRepository itemRepository;

    public void createItem(List<Category> items) {

    }
}

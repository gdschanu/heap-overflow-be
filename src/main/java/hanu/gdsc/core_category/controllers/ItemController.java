package hanu.gdsc.core_category.controllers;


import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.services.ItemService;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/createItem")
    public void createItem(Category[] categoryIds) {
        itemService.create(categoryIds);
    }

    @DeleteMapping("/deleteItem")
    public void deleteItem(Id id) {
        itemService.delete(id);
    }



}

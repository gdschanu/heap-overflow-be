package hanu.gdsc.core_category.controllers;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.services.CategoryService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Tag(name = "Category", description = "Rest-API endpoint for Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public Id createCategory(String name, String serviceToCreate) throws InvalidInputException {
        return categoryService.create(name, serviceToCreate);

    }

    @DeleteMapping("/category/delete/{id}")
    public void deleteCategory(Id id, String serviceToCreate) throws NoSuchFieldException {
        categoryService.delete(id, serviceToCreate);
    }

}

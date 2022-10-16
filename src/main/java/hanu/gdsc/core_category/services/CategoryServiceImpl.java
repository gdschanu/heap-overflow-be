package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.repositories.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(String name) {
        Category category = new Category(Id.generateRandom(), name, "Create Category");
        String saved = category.getServiceToCreate() + "#" + category.getName();
        category.setServiceToCreate(saved);
        return categoryRepository.save(category);
    }

    @Override
    public boolean delete(Id id, String name) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(id));
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return true;
        }
        return false;

    }


}

package hanu.gdsc.core_category.services.category;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryServiceImpl implements CreateCategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public void create(String name) {
        Category category = Category.create(name);
        categoryRepository.save(category);
    }

}

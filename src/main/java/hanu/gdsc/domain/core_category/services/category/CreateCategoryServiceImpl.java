package hanu.gdsc.domain.core_category.services.category;

import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.core_category.repositories.CategoryRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryServiceImpl implements CreateCategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public Id create(Input input) throws InvalidInputException {
        Category category = Category.create(
                input.name,
                input.serviceToCreate
        );
        categoryRepository.save(category);
        return category.getId();
    }


}

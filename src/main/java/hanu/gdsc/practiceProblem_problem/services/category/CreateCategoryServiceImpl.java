package hanu.gdsc.practiceProblem_problem.services.category;

import hanu.gdsc.practiceProblem_problem.domains.Category;
import hanu.gdsc.practiceProblem_problem.repositories.category.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryServiceImpl implements CreateCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Id create(Input input) {
        Category category = Category.create(input.name);
        categoryRepository.create(category);
        return category.getId();
    }
}

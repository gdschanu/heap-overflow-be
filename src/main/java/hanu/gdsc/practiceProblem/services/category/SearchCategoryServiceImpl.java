package hanu.gdsc.practiceProblem.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.repositories.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SearchCategoryServiceImpl implements SearchCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getByName(String name) {
        Category category = categoryRepository.getByName(name);
        if(category == null) {
            throw new BusinessLogicError("This category does not exist", "NOT_FOUND");
        }
        return category;
    }

    @Override
    public Category getById(Id id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

package hanu.gdsc.practiceProblem.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.repositories.category.CategoryRepository;
import hanu.gdsc.share.domains.Id;

import java.util.List;

@Service
public class SearchCategoryServiceImpl implements SearchCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getById(Id id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Category> get() {
        return null;
    }

}

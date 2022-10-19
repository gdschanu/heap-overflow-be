package hanu.gdsc.core_category.services.category;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.repositories.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCategoryServiceImpl implements DeleteCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        categoryRepository.deleteById(id, serviceToCreate);
    }

    @Override
    public void deleteMany(List<Id> categories) {
        categoryRepository.deleteMany(categories);
    }
}



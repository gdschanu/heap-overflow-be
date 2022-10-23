package hanu.gdsc.domain.core_category.services.category;

import hanu.gdsc.domain.core_category.repositories.CategoryRepository;
import hanu.gdsc.domain.share.models.Id;
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



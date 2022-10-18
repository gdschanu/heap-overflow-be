package hanu.gdsc.core_category.services.category;

import hanu.gdsc.core_category.repositories.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryServiceImpl implements DeleteCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void deleteById(Id id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteMany(Id[] ids) {
        categoryRepository.deleteByIds(ids);
    }

}

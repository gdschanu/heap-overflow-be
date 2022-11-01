package hanu.gdsc.infrastructure.core_category.repositories.category;

import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.core_category.repositories.CategoryRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category save(Category category) {
        categoryJpaRepository.save(CategoryEntity.fromDomains(category));
        return category;
    }

    @Override
    public Category findById(Id id) {
        CategoryEntity category = categoryJpaRepository.getById(id.toString());
        return CategoryEntity.toDomain(category);
    }

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        CategoryEntity category = categoryJpaRepository.getById(id.toString());
        categoryJpaRepository.delete(category);
    }

    @Override
    public void deleteMany(List<Id> ids) {

    }
}

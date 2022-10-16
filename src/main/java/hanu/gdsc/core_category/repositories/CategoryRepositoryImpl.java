package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return category.toDomain();
    }

    @Override
    public void deleteById(Id id) {

    }

    @Override
    public void deleteByIds(Id[] ids) {

    }

    @Override
    public void delete(Category category) {
        categoryJpaRepository.delete(CategoryEntity.fromDomains(category));
    }
}

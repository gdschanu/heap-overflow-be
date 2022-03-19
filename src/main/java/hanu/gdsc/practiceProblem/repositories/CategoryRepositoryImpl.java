package hanu.gdsc.practiceProblem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.repositories.JPA.CategoryJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryJPARepository categoryJpaRepository;
    
    @Override
    public void create(Category category) {
        categoryJpaRepository.save(CategoryEntity.toEntity(category));
    }

    @Override
    public Category getByName(String name) {
        CategoryEntity categoryEntity = categoryJpaRepository.getByName(name);
        if (categoryEntity == null) {
            return null;
        }
        return CategoryEntity.toDomain(categoryEntity);
    }

    @Override
    public Category getById(Id id) {
        CategoryEntity categoryEntity = categoryJpaRepository.getById(id.toString());
        if(categoryEntity == null) {
            return null;
        }
        return CategoryEntity.toDomain(categoryEntity);
    }
    
}

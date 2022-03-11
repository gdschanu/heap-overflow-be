package hanu.gdsc.practiceProblem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.repositories.JPA.CategoryJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryJPARepository categoryJpaRepository;
    
    @Override
    public void create(Category category) {
        categoryJpaRepository.save(CategoryEntity.toEntity(category));
    }
    
}

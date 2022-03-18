package hanu.gdsc.practiceProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, String>{
    @Query(value = "SELECT * FROM practice_problem_category c WHERE c.name = :name", nativeQuery = true)
    public CategoryEntity getByName(String name);
}

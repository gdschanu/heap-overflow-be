package hanu.gdsc.practiceProblem.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, UUID>{
    
}

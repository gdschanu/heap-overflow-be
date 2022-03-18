package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.TimeLimitEntity;

public interface TimeLimitJPARepository extends JpaRepository<TimeLimitEntity, String>{
    
}

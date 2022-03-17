package hanu.gdsc.practiceProblem.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemEntity;

public interface PracticeProblemJPARepository extends JpaRepository<PracticeProblemEntity, String>{
    
}

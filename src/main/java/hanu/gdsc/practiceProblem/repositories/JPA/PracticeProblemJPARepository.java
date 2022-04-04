package hanu.gdsc.practiceProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemProblemEntity;

public interface PracticeProblemJPARepository extends JpaRepository<PracticeProblemProblemEntity, String> {
    
}

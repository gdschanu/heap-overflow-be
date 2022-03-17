package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;

public interface ProblemJPARepository extends JpaRepository<ProblemEntity, String> {
    
}

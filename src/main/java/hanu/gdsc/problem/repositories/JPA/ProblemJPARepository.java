package hanu.gdsc.problem.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.problem.repositories.entities.ProblemEntity;

public interface ProblemJPARepository extends JpaRepository<ProblemEntity, UUID> {
    
}

package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;

public interface SubmissionCountJPARepository extends JpaRepository<SubmissionCountEntity, String>{
    
}

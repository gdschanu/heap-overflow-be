package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.problem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemJPARepository extends JpaRepository<ProblemEntity, String> {
    
}

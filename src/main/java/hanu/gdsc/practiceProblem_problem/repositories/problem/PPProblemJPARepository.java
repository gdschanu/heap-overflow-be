package hanu.gdsc.practiceProblem_problem.repositories.problem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PPProblemJPARepository extends JpaRepository<PPProblemEntity, String> {

    @Query("SELECT p FROM PPProblemEntity p ORDER BY function('RAND')")
    Page<PPProblemEntity> getRecommendProblem(Pageable pageable);
    
}

package hanu.gdsc.infrastructure.practiceProblem_problem.repositories.problem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PPProblemJPARepository extends JpaRepository<PPProblemEntity, String> {

    @Query("SELECT p FROM PPProblemEntity p ORDER BY function('RAND')")
    Page<PPProblemEntity> getRecommendProblem(Pageable pageable);

    PPProblemEntity findByCoreProblemProblemId(String id);

    Set<PPProblemEntity> findByCoreProblemProblemIdIn(Iterable<String> ids);

    @Query(value = "select max(t.count) as amount, t.difficulty from ( " +
            "select p.difficulty, " +
            "   count(1) over (partition by p.difficulty order by p.created_at_millis) as \"count\" " +
            "from practice_problem_problem p) t group by t.difficulty;",
        nativeQuery = true)
    List<ProblemCountProjection> countProblemGroupByDifficulty();
}

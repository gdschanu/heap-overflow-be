package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;

public interface SubmissionCountJPARepository extends JpaRepository<SubmissionCountEntity, String>{
    @Query(value="SELECT * FROM core_problem_submission_count sc WHERE sc.problem_id = :problemId", nativeQuery = true)
    public SubmissionCountEntity getById(String problemId);
}

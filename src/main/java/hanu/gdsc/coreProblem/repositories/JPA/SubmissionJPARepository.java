package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;

import java.util.List;
import java.util.UUID;

public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
    @Query(value = "SELECT * FROM core_problem_submission s WHERE s.problemId = :problemId", nativeQuery = true)
    public List<SubmissionEntity> getAllByProblemId(String problemId);
}

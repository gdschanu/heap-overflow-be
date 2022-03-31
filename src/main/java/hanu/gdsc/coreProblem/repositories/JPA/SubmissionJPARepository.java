package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;

import java.util.List;

public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
    @Query(
        value = "SELECT * FROM core_problem_submission s WHERE s.problem_id = :problemId AND s.service_to_create = 'PracticeProblemService'",
        nativeQuery = true)
    public List<SubmissionEntity> getPracticeSubmissions(@Param("problemId") String problemId);
}

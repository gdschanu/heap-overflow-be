package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEventEntity;

public interface EventJPARepository extends JpaRepository<SubmissionEventEntity, String> {
    @Query(value = "SELECT * FROM core_problem_submission_event LIMIT 1", nativeQuery = true)
    public SubmissionEventEntity getEventEntity();
}

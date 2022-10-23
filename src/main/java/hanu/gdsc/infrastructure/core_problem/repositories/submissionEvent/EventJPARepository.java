package hanu.gdsc.infrastructure.core_problem.repositories.submissionEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJPARepository extends JpaRepository<SubmissionEventEntity, String> {
}

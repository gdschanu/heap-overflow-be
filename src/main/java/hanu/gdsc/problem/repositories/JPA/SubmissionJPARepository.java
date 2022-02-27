package hanu.gdsc.problem.repositories.JPA;

import hanu.gdsc.problem.repositories.entities.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, UUID> {
}

package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;

import java.util.UUID;

public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, UUID> {
}

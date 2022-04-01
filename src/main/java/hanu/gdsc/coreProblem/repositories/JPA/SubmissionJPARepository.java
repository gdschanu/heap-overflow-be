package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;

import java.util.List;

public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
}

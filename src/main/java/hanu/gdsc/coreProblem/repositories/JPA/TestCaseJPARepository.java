package hanu.gdsc.coreProblem.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.TestCaseEntity;

public interface TestCaseJPARepository extends JpaRepository<TestCaseEntity, UUID> {
    
}

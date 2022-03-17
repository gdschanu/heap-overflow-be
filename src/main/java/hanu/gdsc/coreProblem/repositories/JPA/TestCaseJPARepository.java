package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.TestCaseEntity;

public interface TestCaseJPARepository extends JpaRepository<TestCaseEntity, String> {
    
}

package hanu.gdsc.coreProblem.repositories.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import hanu.gdsc.coreProblem.repositories.entities.TestCaseEntity;

public interface TestCaseJPARepository extends JpaRepository<TestCaseEntity, String> {
    public List<TestCaseEntity> getByProblemIdAndServiceToCreate(String problemId, String serviceToCreate);

    @Query(value = "SELECT * FROM core_problem_test_case t WHERE t.problem_id = :problemId" +
        " AND t.service_to_create = :serviceToCreate AND t.is_sample = 1" ,
        nativeQuery = true)
    public List<TestCaseEntity> getSampleTestCases(String problemId, String serviceToCreate);

    @Query(value = "SELECT * FROM core_problem_test_case t WHERE t.problem_id = :problemId" +
        " AND t.ordinal = :ordinal AND t.service_to_create = :serviceToCreate",
        nativeQuery = true)
    public TestCaseEntity getByProblemIdAndOrdinalAndServiceToCreate(String problemId, int ordinal, String serviceToCreate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM core_problem_test_case WHERE id = :id AND service_to_create = :serviceToCreate",
        nativeQuery = true)
    public void deleteByIdAndServiceToCreate(String id, String serviceToCreate);
}

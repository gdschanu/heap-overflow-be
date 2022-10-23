package hanu.gdsc.infrastructure.core_problem.repositories.testCase;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestCaseRepositoryImpl implements TestCaseRepository {
    @Autowired
    private TestCaseJPARepository testCaseJpaRepository;
    
    @Override
    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate) {
        List<TestCaseEntity> testCasesEntity = testCaseJpaRepository.getByProblemIdAndServiceToCreate(problemId.toString(), serviceToCreate);
        return testCasesEntity.stream()
                .map(t -> TestCaseEntity.toDomain(t))
                .collect(Collectors.toList());
    }

    @Override
    public void create(TestCase testCase) {
        testCaseJpaRepository.save(TestCaseEntity.toEntity(testCase));
    }

    @Override
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate) {
        List<TestCaseEntity> sampleTestCasesEntity = testCaseJpaRepository.getSampleTestCases(problemId.toString(), serviceToCreate);
        return sampleTestCasesEntity.stream()
            .map(t -> TestCaseEntity.toDomain(t))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Id id, String serviceToDelete) {
        testCaseJpaRepository.deleteByIdAndServiceToCreate(
                id.toString(),
                serviceToDelete
        );

    }

    @Override
    public int count(Id problemId) {
        return testCaseJpaRepository.countByProblemId(problemId.toString());
    }

    @Override
    public void deleteByProblemId(Id id) {
        testCaseJpaRepository.deleteByProblemId(id.toString());
    }

    @Override
    public TestCase getByProblemIdAndOrdinal(Id problemId, int ordinal, String serviceToCreate) {
        return TestCaseEntity.toDomain(
                testCaseJpaRepository.getByProblemIdAndOrdinalAndServiceToCreate(
                        problemId.toString(),
                        ordinal,
                        serviceToCreate)
        );
    }

    @Override
    @Transactional
    public void update(TestCase testCase) {
        testCaseJpaRepository.save(TestCaseEntity.toEntity(testCase));  
    }    
}

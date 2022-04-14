package hanu.gdsc.coreProblem.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.JPA.TestCaseJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.TestCaseEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class TestCaseRepositoryImpl implements TestCaseRepository{
    @Autowired
    private TestCaseJPARepository testCaseJpaRepository;
    
    @Override
    public List<TestCase> getByProblemId(Id id) {
        List<TestCaseEntity> testCasesEntity = testCaseJpaRepository.getByProblemId(id.toString());
        return testCasesEntity.stream()
                .map(t -> TestCaseEntity.toDomain(t))
                .collect(Collectors.toList());
    }

    @Override
    public void create(TestCase testCase) {
        testCaseJpaRepository.save(TestCaseEntity.toEntity(testCase));
    }    
}

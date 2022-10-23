package hanu.gdsc.domain.core_problem.repositories;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface TestCaseRepository {
    public void create(TestCase testCase);

    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate);

    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);

    public TestCase getByProblemIdAndOrdinal(Id problemId, int ordinal, String serviceToCreate);

    public void update(TestCase testCase);

    public void deleteById(Id id, String serviceToDelete);

    public int count(Id problemId);

    public void deleteByProblemId(Id id);
}

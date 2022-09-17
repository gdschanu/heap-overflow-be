package hanu.gdsc.core_problem.repositories.testCase;

import java.util.List;

import hanu.gdsc.core_problem.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface TestCaseRepository {
    public void create(TestCase testCase);
    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate);
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);
    public TestCase getByProblemIdAndOrdinal(Id problemId, int ordinal, String serviceToCreate);
    public void update(TestCase testCase);
    public void deleteById(Id id, String serviceToDelete);

    public int count(Id problemId);
}

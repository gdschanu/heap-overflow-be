package hanu.gdsc.coreProblem.repositories;

import java.util.List;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface TestCaseRepository {
    public void create(TestCase testCase);
    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate);
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);
}

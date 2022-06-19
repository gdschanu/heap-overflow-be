package hanu.gdsc.core_problem.services.testCase;

import java.util.List;

import hanu.gdsc.core_problem.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface SearchTestCaseService {
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);
}

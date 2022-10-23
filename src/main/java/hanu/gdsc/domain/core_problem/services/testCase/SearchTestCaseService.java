package hanu.gdsc.domain.core_problem.services.testCase;

import java.util.List;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.share.models.Id;

public interface SearchTestCaseService {
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);
}

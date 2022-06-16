package hanu.gdsc.coreSubdomain.problemContext.services.testCase;

import java.util.List;

import hanu.gdsc.coreSubdomain.problemContext.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface SearchTestCaseService {
    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate);
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate);
}

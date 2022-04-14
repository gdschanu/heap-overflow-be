package hanu.gdsc.coreProblem.services.testCase;

import java.util.List;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface SearchTestCaseService {
    public List<TestCase> getByProblemId(Id problemId);
}

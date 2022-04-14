package hanu.gdsc.practiceProblem.services.coreProblem;

import java.util.List;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.share.domains.Id;

public interface SearchCoreProblemTestCaseService {
    public List<TestCase> get(Id problemId);
    public List<TestCase> getSampleTestCases(Id problemId);
}

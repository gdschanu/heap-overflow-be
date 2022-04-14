package hanu.gdsc.practiceProblem.services.coreProblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.services.testCase.SearchTestCaseService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.share.domains.Id;

@Service
public class SearchCoreProblemTestCaseServiceImpl implements SearchCoreProblemTestCaseService {
    @Autowired
    private SearchTestCaseService searchTestCaseService;

    @Override
    public List<TestCase> get(Id problemId) {
        return searchTestCaseService.getByProblemId(problemId, ServiceName.serviceName);
    }

    @Override
    public List<TestCase> getSampleTestCases(Id problemId) {
        return searchTestCaseService.getSampleTestCases(problemId, ServiceName.serviceName);
    }
    
}

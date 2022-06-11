package hanu.gdsc.coreProblem.services.testCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class SearchTestCaseServiceImpl implements SearchTestCaseService{
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public List<TestCase> getByProblemId(Id problemId, String serviceToCreate) {
        return testCaseRepository.getByProblemId(problemId, serviceToCreate);
    }

    @Override
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate) {
        return testCaseRepository.getSampleTestCases(problemId, serviceToCreate);
    }
    
}

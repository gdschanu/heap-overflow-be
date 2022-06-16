package hanu.gdsc.core_problem.services.testCase;

import java.util.List;

import hanu.gdsc.core_problem.domains.TestCase;
import hanu.gdsc.core_problem.repositories.testCase.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

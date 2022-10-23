package hanu.gdsc.domain.core_problem.services.testCase;

import java.util.List;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.share.models.Id;

@Service
public class SearchTestCaseServiceImpl implements SearchTestCaseService{
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public List<TestCase> getSampleTestCases(Id problemId, String serviceToCreate) {
        return testCaseRepository.getSampleTestCases(problemId, serviceToCreate);
    }
    
}

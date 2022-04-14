package hanu.gdsc.coreProblem.services.testCase;

import org.springframework.beans.factory.annotation.Autowired;

import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;

public class CreateTestCaseServiceImpl implements CreateTestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public void create(Input input) {
        TestCase testCase = TestCase.create(
            input.problemId, 
            input.input,
            input.expectedOutput,
            input.ordinal,
            input.isSample,
            input.description
        );
        testCaseRepository.create(testCase);
    }
    
}

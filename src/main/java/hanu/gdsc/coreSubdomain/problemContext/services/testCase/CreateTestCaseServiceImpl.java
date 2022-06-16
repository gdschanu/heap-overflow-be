package hanu.gdsc.coreSubdomain.problemContext.services.testCase;

import hanu.gdsc.coreSubdomain.problemContext.domains.TestCase;
import hanu.gdsc.coreSubdomain.problemContext.repositories.testCase.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
            input.description,
            input.serviceToCreate
        );
        testCaseRepository.create(testCase);
    }
    
}

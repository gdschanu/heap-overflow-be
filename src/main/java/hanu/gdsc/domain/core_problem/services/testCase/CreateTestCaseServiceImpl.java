package hanu.gdsc.domain.core_problem.services.testCase;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTestCaseServiceImpl implements CreateTestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public void create(Input input) throws InvalidInputException {
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

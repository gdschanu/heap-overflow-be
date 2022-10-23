package hanu.gdsc.domain.core_problem.services.testCase;

import javax.transaction.Transactional;

import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.core_problem.models.TestCase;
import lombok.*;

@Service
@AllArgsConstructor
public class UpdateTestCaseServiceImpl implements UpdateTestCaseService {

    private final TestCaseRepository testCaseRepository;

    @Override
    @Transactional
    public void update(Input input) throws NotFoundException {
        TestCase oldTestCase = testCaseRepository.getByProblemIdAndOrdinal(input.problemId, input.ordinal, input.serviceToCreate);
        if (oldTestCase == null) {
            throw new NotFoundException("TestCase" + input.ordinal + "doesnt exist");
        }
        if (input.input != null && input.input != oldTestCase.getInput()) {
            oldTestCase.setInput(input.input);
        }
        if (input.expectedOutput != null && input.expectedOutput != oldTestCase.getExpectedOutput()) {
            oldTestCase.setExpectedOutput(input.expectedOutput);
        }
        if (input.ordinal != oldTestCase.getOrdinal()) {
            oldTestCase.setOrdinal(input.ordinal);
        }
        if (input.isSample != oldTestCase.isSample()) {
            oldTestCase.setSample(input.isSample);
        }
        if (input.description != null && input.description != oldTestCase.getDescription()) {
            oldTestCase.setDescription(input.description);
        }
        testCaseRepository.update(oldTestCase);
    }
    
}

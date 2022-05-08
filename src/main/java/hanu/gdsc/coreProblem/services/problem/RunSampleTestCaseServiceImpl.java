package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.coreProblem.repositories.TestCaseRepository;
import hanu.gdsc.share.error.BusinessLogicError;

public class RunSampleTestCaseServiceImpl implements RunSampleTestCaseService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired 
    private TestCaseRepository testCaseRepository;

    @Override
    public Output runSampleTestCase(Input input) {
        Problem problem = problemRepository.getById(input.problemId, input.serviceToCreate);
        if (!problem.getAllowedProgrammingLanguages().contains(input.programmingLanguage)) {
            throw new BusinessLogicError("Problem not support this programming language" + input.programmingLanguage, "LANGUAGE_NOT_SUPPORTED");
        }
        List<TestCase> testCases = testCaseRepository.getSampleTestCases(problem.getId(), input.serviceToCreate);
    }
}
    

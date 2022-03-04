package hanu.gdsc.coreProblem.services.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class CreateProblemServiceImpl implements CreateProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem createProblem(Problem problem) {
        problem.setId(Id.generateRandom());
        for(TestCase testCase : problem.getTestCases()) {
            testCase.setId(Id.generateRandom());
        }
        for(TimeLimit timeLimit : problem.getTimeLimits()) {
            timeLimit.setId(Id.generateRandom());
        }
        for(MemoryLimit memoryLimit : problem.getMemoryLimits()) {
            memoryLimit.setId(Id.generateRandom());
        }
        problemRepository.create(problem);
        return problemRepository.getById(problem.getId());
    }
}

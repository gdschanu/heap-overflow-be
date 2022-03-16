package hanu.gdsc.coreProblem.services.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class UpdateProblemServiceImpl implements UpdateProblemService{
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Id update(Input input) {
        Problem problem = problemRepository.getById(input.id);
        if(problem == null) {
            throw new BusinessLogicError("Bài toán không tồn tại", "NOT_FOUND");
        }
        problem.setName(input.name);
        problem.setDescription(input.description);
        problem.setDifficulty(input.difficulty);
        problem.setTestCases(input.testCases);
        problem.setTimeLimits(input.timeLimits);
        problem.setMemoryLimits(input.memoryLimits);
        problem.setAllowedProgrammingLanguages(input.allowedProgrammingLanguages);
        problemRepository.update(problem);
        return problem.getId();
    }
    
}

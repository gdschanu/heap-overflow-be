package hanu.gdsc.practiceProblem.services.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.error.BusinessLogicError;

@Component(value = "PracticeProblem.UpdateProblemServiceImpl")
public class UpdateProblemServiceImpl implements UpdateProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public void update(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new BusinessLogicError("problem doesn't exist.", "NOT_FOUND");
        }
        if(input.coreProblemId != null) {
            problem.setCoreProblemId(input.coreProblemId);
        }
        if(input.categoryIds != null) {
            problem.setCategoryIds(input.categoryIds);
        }
        if(input.difficulty != null) {
            problem.setDifficulty(input.difficulty);
        }
        problemRepository.update(problem);
    } 
}

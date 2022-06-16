package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.UpdateProblemServiceImpl")
public class UpdateProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id problemId;
        public Id coreProblemId;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    public void update(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new BusinessLogicError("problem doesn't exist.", "NOT_FOUND");
        }
        if (input.coreProblemId != null) {
            problem.setCoreProblemId(input.coreProblemId);
        }
        if (input.categoryIds != null) {
            problem.setCategoryIds(input.categoryIds);
        }
        if (input.difficulty != null) {
            problem.setDifficulty(input.difficulty);
        }
        problemRepository.update(problem);
    }
}

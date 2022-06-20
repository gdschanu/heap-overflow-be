package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProblemServiceImpl implements UpdateProblemService{
    private ProblemRepository problemRepository;

    @Override
    public void update(Input input) {
        Problem problem = problemRepository.getById(input.id, input.serviceToCreate);
        if (problem == null) {
            throw new NotFoundError("Unknown problem");
        }
        if (input.name != null) {
            problem.setName(input.name);
        }
        if (input.description != null) {
            problem.setDescription(input.description);
        }
        if (input.memoryLimits != null) {
            problem.clearMemoryLimits();
            for (MemoryLimit.CreateInput inp : input.memoryLimits) {
                problem.addMemoryLimit(MemoryLimit.create(inp));
            }
        }
        if (input.timeLimits != null) {
            problem.clearTimeLimits();
            for (TimeLimit.CreateInput inp : input.timeLimits) {
                problem.addTimeLimit(TimeLimit.create(inp));
            }
        }
        if (input.allowedProgrammingLanguages != null) {
            problem.setAllowedProgrammingLanguages(input.allowedProgrammingLanguages);
        }
        problemRepository.update(problem);
    }
}


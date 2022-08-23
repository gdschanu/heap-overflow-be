package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
            problem.setMemoryLimits(input.memoryLimits.stream()
                    .map(inp -> MemoryLimit.create(inp))
                    .collect(Collectors.toList()));
        }
        if (input.timeLimits != null) {
            problem.setTimeLimits(input.timeLimits.stream()
                    .map(inp -> TimeLimit.create(inp))
                    .collect(Collectors.toList()));
        }
        if (input.allowedProgrammingLanguages != null) {
            problem.setAllowedProgrammingLanguages(input.allowedProgrammingLanguages);
        }
        problemRepository.update(problem);
    }
}


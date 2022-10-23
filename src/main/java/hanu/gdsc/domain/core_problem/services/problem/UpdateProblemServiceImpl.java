package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.core_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateProblemServiceImpl implements UpdateProblemService{
    private ProblemRepository problemRepository;

    @Override
    public void update(Input input) throws NotFoundException, InvalidInputException {
        Problem problem = problemRepository.getById(input.id, input.serviceToCreate);
        if (problem == null) {
            throw new NotFoundException("Unknown problem");
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
            final List<TimeLimit> timeLimits = new ArrayList<>();
            for (TimeLimit.CreateInputTL inputTL : input.timeLimits)
                timeLimits.add(TimeLimit.create(inputTL));
            problem.setTimeLimits(timeLimits);
        }
        if (input.allowedProgrammingLanguages != null) {
            problem.setAllowedProgrammingLanguages(input.allowedProgrammingLanguages);
        }
        problemRepository.update(problem);
    }
}


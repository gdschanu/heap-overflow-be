package hanu.gdsc.domain.practiceProblem_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.UpdateProblemServiceImpl")
@AllArgsConstructor
public class UpdateProblemService {
    private final ProblemRepository problemRepository;
    private final hanu.gdsc.domain.core_problem.services.problem.UpdateProblemService updateCoreProblemService;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class InputUpdate {
        public Id problemId;
        public Difficulty difficulty;
        public String name;
        public String description;
        public List<MemoryLimit.CreateInputML> memoryLimits;
        public List<TimeLimit.CreateInputTL> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(InputUpdate input) throws NotFoundException, InvalidInputException {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new NotFoundException("problem doesn't exist.");
        }
        if (input.difficulty != null) {
            problem.setDifficulty(input.difficulty);
        }
        problemRepository.update(problem);
        updateCoreProblemService.update(new hanu.gdsc.domain.core_problem.services.problem.UpdateProblemService.Input(
                problem.getCoreProblemProblemId(),
                ServiceName.serviceName,
                input.name,
                input.description,
                input.memoryLimits,
                input.timeLimits,
                input.allowedProgrammingLanguages
        ));
    }
}

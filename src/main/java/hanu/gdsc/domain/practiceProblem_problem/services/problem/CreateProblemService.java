package hanu.gdsc.domain.practiceProblem_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.CreateProblemServiceImpl")
@AllArgsConstructor
public class CreateProblemService {
    private final hanu.gdsc.domain.core_problem.services.problem.CreateProblemService createCoreProblemService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Difficulty difficulty;
        public String name;
        public String description;
        public Integer point;
        public List<MemoryLimit.CreateInputML> memoryLimits;
        public List<TimeLimit.CreateInputTL> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    public Id create(Input input) throws InvalidInputException {
        Id coreProblemId = createCoreProblemService.create(new hanu.gdsc.domain.core_problem.services.problem.CreateProblemService.Input(
                input.name,
                input.description,
                input.author,
                input.memoryLimits,
                input.timeLimits,
                input.allowedProgrammingLanguages,
                PracticeProblemProblemServiceName.serviceName
        ));
        Problem practiceProblem = Problem.create(coreProblemId, input.difficulty, input.point);
        problemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
    
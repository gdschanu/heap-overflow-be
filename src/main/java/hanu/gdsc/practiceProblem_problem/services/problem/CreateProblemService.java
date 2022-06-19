package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.CreateProblemServiceImpl")
@AllArgsConstructor
public class CreateProblemService {
    private final hanu.gdsc.core_problem.services.problem.CreateProblemService createCoreProblemService;
    private final ProblemRepository problemRepository;

    @Builder
    public static class Input {
        public Difficulty difficulty;
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    public Id create(Input input) {
        Id coreProblemId = createCoreProblemService.execute(new hanu.gdsc.core_problem.services.problem.CreateProblemService.Input(
                input.name,
                input.description,
                input.author,
                input.createMemoryLimitInputs,
                input.createTimeLimitInputs,
                input.allowedProgrammingLanguages,
                ServiceName.serviceName
        ));
        Problem practiceProblem = Problem.create(coreProblemId, input.difficulty);
        problemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
    
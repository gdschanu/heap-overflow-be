package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.UpdateProblemServiceImpl")
@AllArgsConstructor
public class UpdateProblemService {
    private final ProblemRepository problemRepository;
    private final hanu.gdsc.core_problem.services.problem.UpdateProblemService updateCoreProblemService;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "Update", description = "Data transfer object for PracticeProblem to update")
    public static class InputUpdate {
        @Schema(description = "specify the id of problem", example = "627778618119e29412c16201", required = true)
        public Id problemId;
        @Schema(description = "specify the difficulty of problem", example = "EASY", required = true)
        public Difficulty difficulty;
        @Schema(description = "specify the name of problem", example = "Sum Array", required = true)
        public String name;
        @Schema(description = "specify the description of problem", example = "blablalbalba", required = true)
        public String description;
        public List<MemoryLimit.CreateInputML> memoryLimits;
        public List<TimeLimit.CreateInputTL> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(InputUpdate input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new BusinessLogicError("problem doesn't exist.", "NOT_FOUND");
        }
        if (input.difficulty != null) {
            problem.setDifficulty(input.difficulty);
        }
        problemRepository.update(problem);
        updateCoreProblemService.update(new hanu.gdsc.core_problem.services.problem.UpdateProblemService.Input(
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

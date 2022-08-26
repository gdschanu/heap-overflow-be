package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component(value = "PracticeProblem.UpdateProblemService")
@Tag(name = "Practice Problem - Problem" , description = "Rest-API endpoint for Practice Problem")
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "Update", description = "Data transfer object for PracticeProblem to update")
    public static class InputUpdate {
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

    @PutMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody InputUpdate input,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            updateProblemService.update(new UpdateProblemService.InputUpdate(
                    new Id(id),
                    input.difficulty,
                    input.name,
                    input.description,
                    input.memoryLimits,
                    input.timeLimits,
                    input.allowedProgrammingLanguages
            ));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

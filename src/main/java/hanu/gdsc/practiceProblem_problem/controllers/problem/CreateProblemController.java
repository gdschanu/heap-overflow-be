package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.services.problem.CreateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Practice Problem-Problem" , description = "Rest-API endpoint for Practice Problem")
public class CreateProblemController {
    @Autowired
    private CreateProblemService createPracticeProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "Create", description = "Data transfer object for PracticeProblem to create" )
    public static class InputCreateProblem{
        @Schema(description = "specify the difficulty of problem", example = "EASY", required = true)
        public Difficulty difficulty;
        @Schema(description = "specify the name of problem", example = "Calculate Sum array", required = true)
        public String name;
        @Schema(description = "specify the description of problem", example = "blalblalba", required = true)
        public String description;
        @Schema
        public List<MemoryLimit.CreateInputML> memoryLimits;
        @Schema
        public List<TimeLimit.CreateInputTL> timeLimits;
        @Schema
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @Operation(
            summary = "Create the practice problem",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully created.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Id.class, example = "hihi")
                            )
                    }
            ), @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "invalid request"
            )}
    )
    @PostMapping("/practiceProblem/problem")
    public ResponseEntity<?> create(@RequestBody InputCreateProblem input) {
        return ControllerHandler.handle(() -> {
//            Id coderId = authorizeService.authorize(token);
            Id problemId = createPracticeProblemService.create(new CreateProblemService.Input(
                    input.difficulty,
                    input.name,
                    input.description,
                    input.memoryLimits,
                    input.timeLimits,
                    input.allowedProgrammingLanguages,
                    Id.generateRandom()
            ));
            return new ControllerHandler.Result(
                    "Success",
                    problemId
            );
        });
    }
}

package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.problem;

import hanu.gdsc.domain.coderAuth.services.AuthorizeService;
import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problem.services.problem.CreateProblemService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
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
@Tag(name = "Practice Problem - Problem" , description = "Rest-API endpoint for Practice Problem")
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
                                    schema = @Schema(example = "123123123a213123")
                            )
                    }
            ), @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content()}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "invalid request",
                    content = {@Content()}
            )}
    )
    @PostMapping("/practiceProblem/problem")
    public ResponseEntity<?> create(@RequestBody InputCreateProblem input, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            Id problemId = createPracticeProblemService.create(new CreateProblemService.Input(
                    input.difficulty,
                    input.name,
                    input.description,
                    input.memoryLimits,
                    input.timeLimits,
                    input.allowedProgrammingLanguages,
                    coderId
            ));
            return new ControllerHandler.Result(
                    "Success",
                    problemId
            );
        });
    }
}

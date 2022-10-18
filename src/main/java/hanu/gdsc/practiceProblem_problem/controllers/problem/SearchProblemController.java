package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@Tag(name = "Practice Problem - Problem", description = "Rest-API endpoint for Practice Problem")
public class SearchProblemController {
    @Autowired
    private SearchProblemService servicePracticeProblemService;
    @Autowired
    private AuthorizeService authorizeService;


    @Operation(
            summary = "Search the practice problem by id",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SearchProblemService.Output.class)
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
    @GetMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> getById(@PathVariable String id,
                                     @RequestHeader(name = "access-token", required = false) String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = token == null ? null : authorizeService.authorize(token);
            SearchProblemService.Output output = servicePracticeProblemService.getById(new Id(id), coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestHeader(name = "access-token", required = false) String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = token == null ? null : authorizeService.authorize(token);
            List<SearchProblemService.Output> output = servicePracticeProblemService.get(page, perPage, coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem/count")
    public ResponseEntity<?> countProblem() {
        return ControllerHandler.handle(() -> {
            long output = servicePracticeProblemService.countProblem();
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem/recommended")
    public ResponseEntity<?> getRecommendProblem(@RequestParam int count,
                                                 @RequestHeader(name = "access-token", required = false) String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = token == null ? null : authorizeService.authorize(token);
            List<SearchProblemService.Output> output = servicePracticeProblemService.getRecommendProblem(count, coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem/progress")
    public ResponseEntity<?> getProgress(@RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            List<SearchProblemService.OutputProgressData> output = servicePracticeProblemService.getProgress(coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}
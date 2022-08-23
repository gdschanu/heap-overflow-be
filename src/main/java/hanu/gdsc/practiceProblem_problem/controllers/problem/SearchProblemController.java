package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Practice Problem-Problem" , description = "Rest-API endpoint for Practice Problem")
public class SearchProblemController {
    @Autowired
    private SearchProblemService servicePracticeProblemService;

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
    public ResponseEntity<?> getById(@PathVariable String id){
        return ControllerHandler.handle(() -> {
            SearchProblemService.Output output = servicePracticeProblemService.getById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage) {
        return ControllerHandler.handle(() -> {
            List<SearchProblemService.Output> output = servicePracticeProblemService.get(page, perPage);
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
    public ResponseEntity<?> getRecommendProblem(@RequestParam  int count) {
        return ControllerHandler.handle(() -> {
            List<SearchProblemService.Output> output = servicePracticeProblemService.getRecommendProblem(count);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}
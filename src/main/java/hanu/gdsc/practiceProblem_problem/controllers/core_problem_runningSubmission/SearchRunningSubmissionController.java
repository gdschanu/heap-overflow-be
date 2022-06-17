package hanu.gdsc.practiceProblem_problem.controllers.core_problem_runningSubmission;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchRunningSubmissionController {
    private SearchRunningSubmissionService searchRunningSubmissionService;
    private AuthorizeService authorizeService;

    @GetMapping("/practiceProblem/coreProblem/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output> output = searchRunningSubmissionService.getByCoderId(page, perPage, coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/coreProblem/runningSubmission/{id}")
    public ResponseEntity<?> getById(@PathVariable String id, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output output = searchRunningSubmissionService.getByIdAndCoderId(new Id(id), coderId);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

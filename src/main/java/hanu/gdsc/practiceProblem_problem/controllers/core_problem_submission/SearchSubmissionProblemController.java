package hanu.gdsc.practiceProblem_problem.controllers.core_problem_submission;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.services.submission.SearchSubmissionService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_submission.SearchCoreProblemSubmissionService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.InvalidInputError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchSubmissionProblemController {
    @Autowired
    private SearchCoreProblemSubmissionService searchCoreProblemSubmissionService;
    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/practiceProblem/coreProblem/submission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestParam(required = false, name = "token") String tokenReqParam,
                                 @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            if (!token.equals(tokenReqParam)) {
                throw new InvalidInputError("token in request param must be equals to access-token");
            }
            authorizeService.authorize(token);
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = tokenReqParam == null ? null : authorizeService.authorize(token);
            List<SearchSubmissionService.Output> output = searchCoreProblemSubmissionService.get(page, perPage, problem, coder);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/coreProblem/submission/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ControllerHandler.handle(() -> {
            SearchSubmissionService.Output output = searchCoreProblemSubmissionService.getById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

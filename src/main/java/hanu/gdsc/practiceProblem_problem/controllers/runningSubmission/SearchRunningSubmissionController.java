package hanu.gdsc.practiceProblem_problem.controllers.runningSubmission;

import hanu.gdsc.practiceProblem_problem.services.runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Practice Problem-Running Submission", description = "Rest-API endpoint for Practice Problem")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchRunningSubmissionService searchRunningSubmissionService;

    @GetMapping("/practiceProblem/submission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output> output =
                    searchRunningSubmissionService
                            .getRunningSubmissions(problem, coder, page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

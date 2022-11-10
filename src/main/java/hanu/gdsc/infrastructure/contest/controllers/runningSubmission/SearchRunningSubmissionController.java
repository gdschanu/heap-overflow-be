package hanu.gdsc.infrastructure.contest.controllers.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.services.runningSubmission.SearchContestRunningSubmissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Contest", description = "Rest-API endpoint for Contest")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchContestRunningSubmissionService searchContestRunningSubmissionService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/contest/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<hanu.gdsc.domain.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output> output =
                    searchPracticeProblemRunningSubmissionService
                            .getRunningSubmissions(problem, coder, page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

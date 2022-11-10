package hanu.gdsc.infrastructure.contest.controllers.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.services.runningSubmission.SearchContestRunningSubmissionService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Contest", description = "Rest-API endpoint for Contest")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchContestRunningSubmissionService searchContestRunningSubmissionService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/contest/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "contestId") String contestId,
                                 @RequestParam(required = false, name = "contestProblemOrdinal") Integer contestProblemOrdinal,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id contest = contestId == null ? null : new Id(contestId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<SearchContestRunningSubmissionService.Output> output =
                    searchContestRunningSubmissionService
                            .getRunningSubmissions(
                                    contest,
                                    contestProblemOrdinal,
                                    coder,
                                    page,
                                    perPage
                            );
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

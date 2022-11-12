package hanu.gdsc.infrastructure.contest.controllers.testCase;

import hanu.gdsc.domain.contest.services.testCase.SearchContestTestCaseService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Contest - TestCase", description = "Rest-API endpoint for Contest")
public class SearchContestTestCaseController {
    @Autowired
    private SearchContestTestCaseService searchContestTestCaseService;

    @GetMapping("/contest/{contestId}/problem/{contestProblemOrdinal}/testCase/sample")
    public ResponseEntity<?> getSampleTestCases(@PathVariable("contestId") String contestId,
                                                @PathVariable("contestProblemOrdinal") int contestProblemOrdinal) {
        return ControllerHandler.handle(() -> {
            List<SearchContestTestCaseService.Output> outputs = searchContestTestCaseService
                    .getSampleTestCasesOfContestProblem(new Id(contestId), contestProblemOrdinal);
            return new ControllerHandler.Result(
                    "Success",
                    outputs
            );
        });
    }
}

package hanu.gdsc.practiceProblem_problem.controllers.core_problem_testCase;

import hanu.gdsc.practiceProblem_problem.services.core_problem_testCase.SearchCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchTestCaseProblemController {
    @Autowired
    private SearchCoreProblemTestCaseService searchCoreProblemTestCaseService;

    @GetMapping("/practiceProblem/coreProblem/testCase/{problemId}/isSample")
    public ResponseEntity<?> getSampleTestCases(@PathVariable String problemId) {
        return ControllerHandler.handle(() -> {
            List<SearchCoreProblemTestCaseService.Output> outputs = searchCoreProblemTestCaseService.getSampleTestCases(new hanu.gdsc.share.domains.Id(problemId));
            return new ControllerHandler.Result(
                    "Success",
                    outputs
            );
        });
    }
}

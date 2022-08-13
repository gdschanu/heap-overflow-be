package hanu.gdsc.practiceProblem_problem.controllers.testCase;

import hanu.gdsc.practiceProblem_problem.services.testCase.SearchTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Practice Problem-TestCase" , description = "Rest-API endpoint for Practice Problem")
public class SearchTestCaseController {
    @Autowired
    private SearchTestCaseService searchTestCaseService;

    @GetMapping("/practiceProblem/testCase/sample/{problemId}")
    public ResponseEntity<?> getSampleTestCases(@PathVariable String problemId) {
        return ControllerHandler.handle(() -> {
            List<SearchTestCaseService.Output> outputs = searchTestCaseService.getSampleTestcasesOfProblem(new hanu.gdsc.share.domains.Id(problemId));
            return new ControllerHandler.Result(
                    "Success",
                    outputs
            );
        });
    }
}

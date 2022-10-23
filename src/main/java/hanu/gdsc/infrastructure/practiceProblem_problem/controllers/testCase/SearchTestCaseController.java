package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.testCase;

import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problem.services.testCase.SearchTestCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Practice Problem - TestCase" , description = "Rest-API endpoint for Practice Problem")
public class SearchTestCaseController {
    @Autowired
    private SearchTestCaseService searchTestCaseService;

    @GetMapping("/practiceProblem/testCase/sample/{problemId}")
    public ResponseEntity<?> getSampleTestCases(@PathVariable String problemId) {
        return ControllerHandler.handle(() -> {
            List<SearchTestCaseService.Output> outputs = searchTestCaseService.getSampleTestcasesOfProblem(new Id(problemId));
            return new ControllerHandler.Result(
                    "Success",
                    outputs
            );
        });
    }
}

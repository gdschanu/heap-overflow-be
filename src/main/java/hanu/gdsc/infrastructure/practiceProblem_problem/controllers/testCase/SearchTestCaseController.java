package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.testCase;

import hanu.gdsc.domain.practiceProblem_problem.services.testCase.SearchPracticeProblemTestCaseService;
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
@Tag(name = "Practice Problem - TestCase" , description = "Rest-API endpoint for Practice Problem")
public class SearchTestCaseController {
    @Autowired
    private SearchPracticeProblemTestCaseService searchPracticeProblemTestCaseService;

    @GetMapping("/practiceProblem/problem/{problemId}/testCase/sample")
    public ResponseEntity<?> getSampleTestCases(@PathVariable String problemId) {
        return ControllerHandler.handle(() -> {
            List<SearchPracticeProblemTestCaseService.Output> outputs = searchPracticeProblemTestCaseService
                    .getSampleTestcasesOfProblem(new Id(problemId));
            return new ControllerHandler.Result(
                    "Success",
                    outputs
            );
        });
    }
}

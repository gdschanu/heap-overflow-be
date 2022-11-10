package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.testCase;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.practiceProblem_problem.services.testCase.CreatePracticeProblemTestCaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Practice Problem - TestCase" , description = "Rest-API endpoint for Practice Problem")
public class CreateTestCaseController {
    @Autowired
    private CreatePracticeProblemTestCaseService service;
    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/practiceProblem/testCase")
    public ResponseEntity<?> create(@RequestBody CreatePracticeProblemTestCaseService.InputCreate input, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            service.execute(input);
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

package hanu.gdsc.practiceProblem_problem.controllers.testCase;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.testCase.CreateTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
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
    private CreateTestCaseService service;
    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/practiceProblem/testCase")
    public ResponseEntity<?> create(@RequestBody CreateTestCaseService.InputCreate input, @RequestHeader("access-token") String token) {
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

package hanu.gdsc.infrastructure.contest.controllers.testCase;

import hanu.gdsc.domain.contest.services.testCase.CreateContestTestCaseService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Contest - TestCase", description = "Rest-API endpoint for Practice Problem")
public class CreateContestTestCaseController {
    @Autowired
    private CreateContestTestCaseService service;

    @PostMapping("/contest/testCase")
    public ResponseEntity<?> create(@RequestBody CreateContestTestCaseService.InputCreate input,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            service.create(input);
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

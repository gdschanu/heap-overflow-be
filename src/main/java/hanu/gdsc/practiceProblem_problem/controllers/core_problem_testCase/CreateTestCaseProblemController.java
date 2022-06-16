package hanu.gdsc.practiceProblem_problem.controllers.core_problem_testCase;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_testCase.CreateCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CreateTestCaseProblemController {
    @Autowired
    private CreateCoreProblemTestCaseService createCoreProblemTestCaseService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class CreateTestCaseInput {
        public String problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @PostMapping("/practiceProblem/coreProblem/testCase")
    public ResponseEntity<?> create(@RequestBody List<CreateTestCaseInput> createTestCaseInputs,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            createCoreProblemTestCaseService.create(createTestCaseInputs.stream()
                    .map(c -> CreateCoreProblemTestCaseService.Input.builder()
                            .problemId(new hanu.gdsc.share.domains.Id(c.problemId))
                            .input(c.input)
                            .expectedOutput(c.expectedOutput)
                            .ordinal(c.ordinal)
                            .isSample(c.isSample)
                            .description(c.description)
                            .build()
                    )
                    .collect(Collectors.toList()));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

package hanu.gdsc.practiceProblem_problem.controllers.core_problem_testCase;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.services.testCase.UpdateTestCaseService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_testCase.UpdateCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateTestCaseProblemController {
    @Autowired
    private UpdateCoreProblemTestCaseService updateCoreProblemProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class Input {
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @PutMapping("/practiceProblem/coreProblem/testCase/{problemId}/update/{ordinal}")
    public ResponseEntity<?> update(@PathVariable("problemId") String problemId,
                                    @PathVariable("ordinal") int ordinal,
                                    @RequestBody Input input,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            updateCoreProblemProblemService.update(UpdateTestCaseService.Input.builder()
                    .problemId(new Id(problemId))
                    .input(input.input)
                    .expectedOutput(input.expectedOutput)
                    .ordinal(ordinal)
                    .isSample(input.isSample)
                    .description(input.description)
                    .build()
            );
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

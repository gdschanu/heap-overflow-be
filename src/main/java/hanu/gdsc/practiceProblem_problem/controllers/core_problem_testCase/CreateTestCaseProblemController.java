package hanu.gdsc.practiceProblem_problem.controllers.core_problem_testCase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem_problem.services.core_problem_testCase.CreateCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class CreateTestCaseProblemController {
    @Autowired
    private CreateCoreProblemTestCaseService createCoreProblemTestCaseService;

    public static class CreateTestCaseInput {
        public String problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @PostMapping("/practiceProblem/coreProblem/testCase")
    public ResponseEntity<?> create(@RequestBody List<CreateTestCaseInput> createTestCaseInputs) {
        try {
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
            return new ResponseEntity<>(new ResponseBody("Create successfully"), HttpStatus.OK);
        } catch(Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

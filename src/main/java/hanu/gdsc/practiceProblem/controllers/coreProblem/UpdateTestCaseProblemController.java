package hanu.gdsc.practiceProblem.controllers.coreProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.coreProblem.services.testCase.UpdateTestCaseService;
import hanu.gdsc.practiceProblem.services.coreProblem.UpdateCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class UpdateTestCaseProblemController {
    @Autowired
    private UpdateCoreProblemTestCaseService updateCoreProblemProblemService;

    public static class Input {
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @PutMapping("/practiceProblem/coreProblem/testCase/{problemId}/update/{ordinal}")
    public ResponseEntity<?> update(@PathVariable("problemId") String problemId, @PathVariable("ordinal") int ordinal, @RequestBody Input input) {
        try {
            updateCoreProblemProblemService.update(UpdateTestCaseService.Input.builder()
                .problemId(new Id(problemId))
                .input(input.input)
                .expectedOutput(input.expectedOutput)
                .ordinal(ordinal)
                .isSample(input.isSample)
                .description(input.description)
                .build()
            );
            return new ResponseEntity<>(
                new ResponseBody("Update successfully"), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

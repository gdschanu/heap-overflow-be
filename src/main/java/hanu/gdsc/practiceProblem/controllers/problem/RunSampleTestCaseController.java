package hanu.gdsc.practiceProblem.controllers.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.practiceProblem.services.problem.RunSampleTestCaseService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class RunSampleTestCaseController {
    @Autowired
    private RunSampleTestCaseService runSampleTestCaseService;

    public static class Input {
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @PostMapping("/practiceProblem/problem/{id}/runSampleTestCase")
    public ResponseEntity<?> runSampleTestCase(@PathVariable String id, @RequestBody Input input) {
        try {
            hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService.Output output = runSampleTestCaseService.runSampleTestCase(
                RunSampleTestCaseService.Input.builder()
                    .coderId(Id.generateRandom())
                    .problemId(new Id(id))
                    .code(input.code)
                    .programmingLanguage(input.programmingLanguage)
                    .build()
                );
            return new ResponseEntity<>(
                new ResponseBody("run code successfully", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

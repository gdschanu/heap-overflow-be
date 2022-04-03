package hanu.gdsc.practiceProblem.controllers.problem;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.practiceProblem.services.problem.SubmitProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmitProblemController {
    @Autowired
    private SubmitProblemService submitProblemService;

    public static class Input {
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @PostMapping("/practiceProblem/problem/submit")
    public ResponseEntity<?> submit(@RequestBody Input input) {
        try {
            SubmitService.Output output = submitProblemService.submit(SubmitProblemService.Input.builder()
                    .problemId(input.problemId)
                    .code(input.code)
                    .programmingLanguage(input.programmingLanguage)
                    .coderId(Id.generateRandom())
                    .build());
            return new ResponseEntity<>(
                    new ResponseBody("Submit successfully", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

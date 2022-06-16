package hanu.gdsc.practiceProblem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.services.submit.SubmitService;
import hanu.gdsc.practiceProblem.services.problem.SubmitProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmitProblemController {
    @Autowired
    private SubmitProblemService submitProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class Input {
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @PostMapping("/practiceProblem/problem/{id}/submit")
    public ResponseEntity<?> submit(@RequestBody Input input, @PathVariable String id,
                                    @RequestHeader("acces-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            SubmitService.Output output = submitProblemService.submit(SubmitProblemService.Input.builder()
                    .problemId(new Id(id))
                    .code(input.code)
                    .programmingLanguage(input.programmingLanguage)
                    .coderId(coderId)
                    .build());
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

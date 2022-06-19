package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component(value = "PracticeProblem.UpdateProblemService")
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    @PutMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateProblemService.Input input,
                                    @RequestHeader("acces-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            updateProblemService.update(input);
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

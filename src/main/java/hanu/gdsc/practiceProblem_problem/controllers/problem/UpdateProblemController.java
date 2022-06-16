package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component(value = "PracticeProblem.UpdateProblemService")
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class UpdateInput {
        public Id coreProblemId;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    @PutMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateInput input,
                                    @RequestHeader("acces-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            updateProblemService.update(UpdateProblemService.Input.builder()
                    .problemId(new Id(id))
                    .coreProblemId(input.coreProblemId)
                    .categoryIds(input.categoryIds)
                    .difficulty(input.difficulty)
                    .build());
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

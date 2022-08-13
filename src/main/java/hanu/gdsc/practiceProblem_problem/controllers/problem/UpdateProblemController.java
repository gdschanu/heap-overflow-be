package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component(value = "PracticeProblem.UpdateProblemService")
@Tag(name = "Practice Problem-Problem" , description = "Rest-API endpoint for Practice Problem")
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    @PutMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateProblemService.Input input,
                                    @RequestHeader("access-token") String token) {
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

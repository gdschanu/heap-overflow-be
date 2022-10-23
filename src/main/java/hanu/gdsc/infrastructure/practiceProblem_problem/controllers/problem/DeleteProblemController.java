package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.problem;

import hanu.gdsc.domain.practiceProblem_problem.services.problem.DeleteProblemService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Practice Problem - Problem" , description = "Rest-API endpoint for Practice Problem")
public class DeleteProblemController {
    @Autowired
    private DeleteProblemService deleteProblemService;

    @DeleteMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ControllerHandler.handle(() -> {
            deleteProblemService.deleteById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

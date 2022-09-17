package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.practiceProblem_problem.services.problem.DeleteProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

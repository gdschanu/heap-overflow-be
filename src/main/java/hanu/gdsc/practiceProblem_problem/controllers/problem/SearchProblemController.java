package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchProblemController {
    @Autowired
    private SearchProblemService servicePracticeProblemService;

    @GetMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        return ControllerHandler.handle(() -> {
            SearchProblemService.Output output = servicePracticeProblemService.getById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @GetMapping("/practiceProblem/problem")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage) {
        return ControllerHandler.handle(() -> {
            List<SearchProblemService.Output> output = servicePracticeProblemService.get(page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}
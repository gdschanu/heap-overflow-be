package hanu.gdsc.practiceProblem_problem.controllers.core_problem_problem;

import hanu.gdsc.core_problem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_problem.SearchCoreProblemProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "PracticeProblem.CoreProblem.SearchProblemController")
public class SearchProblemController {
    @Autowired
    private SearchCoreProblemProblemService service;

    @GetMapping("/practiceProblem/coreProblem/problem/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ControllerHandler.handle(() -> {
            SearchProblemService.Output res = service.getById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    res
            );
        });
    }

    @GetMapping("/practiceProblem/coreProblem/problem")
    public ResponseEntity<?> getByIds(@RequestParam String ids) {
        return ControllerHandler.handle(() -> {
            List<Id> uids = Arrays
                    .stream(ids.split(","))
                    .map(x -> new Id(x))
                    .collect(Collectors.toList());
            List<SearchProblemService.Output> res = service.getByIds(uids);
            return new ControllerHandler.Result(
                    "Success",
                    res
            );
        });
    }
}

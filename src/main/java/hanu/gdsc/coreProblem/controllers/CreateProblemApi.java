package hanu.gdsc.coreProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.services.problem.CreateProblemService;

@RestController
public class CreateProblemApi {
    @Autowired
    private CreateProblemService createProblemService;

    @PostMapping("/problem/create")
    public Problem createProblem(@RequestBody Problem problem) {
        Problem problemCreated = createProblemService.createProblem(problem);
        return problemCreated; 
    }
}

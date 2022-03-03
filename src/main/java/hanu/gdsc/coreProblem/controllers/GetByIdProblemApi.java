package hanu.gdsc.coreProblem.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.services.problem.GetByIdProblemService;
import hanu.gdsc.share.domains.Id;

@RestController
public class GetByIdProblemApi {
    @Autowired
    private GetByIdProblemService getByIdProblemService;

    @GetMapping("/problem/{uuid}")
    public Problem getById(@PathVariable("id") String id) {
        return getByIdProblemService.getById(new Id(id));
    }
}

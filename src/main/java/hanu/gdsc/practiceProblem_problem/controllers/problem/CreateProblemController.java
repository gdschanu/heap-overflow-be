package hanu.gdsc.practiceProblem_problem.controllers.problem;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.services.problem.CreateProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreateProblemController {
    @Autowired
    private CreateProblemService createPracticeProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class Input {
        public Difficulty difficulty;
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> memoryLimits;
        public List<TimeLimit.CreateInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @PostMapping("/practiceProblem/problem")
    public ResponseEntity<?> create(@RequestBody Input input, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            Id problemId = createPracticeProblemService.create(new CreateProblemService.Input(
                    input.difficulty,
                    input.name,
                    input.description,
                    input.memoryLimits,
                    input.timeLimits,
                    input.allowedProgrammingLanguages,
                    coderId
            ));
            return new ControllerHandler.Result(
                    "Success",
                    problemId
            );
        });
    }
}

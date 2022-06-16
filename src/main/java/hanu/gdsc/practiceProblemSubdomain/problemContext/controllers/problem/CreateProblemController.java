package hanu.gdsc.practiceProblemSubdomain.problemContext.controllers.problem;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.services.AuthorizeService;
import hanu.gdsc.coreSubdomain.problemContext.domains.MemoryLimit;
import hanu.gdsc.coreSubdomain.problemContext.domains.ProgrammingLanguage;
import hanu.gdsc.coreSubdomain.problemContext.domains.TimeLimit;
import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Difficulty;
import hanu.gdsc.practiceProblemSubdomain.problemContext.services.problem.CreateProblemService;
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
        public CreateCoreProblemInput createCoreProblemInput;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;   
    }

    @PostMapping("/practiceProblem/problem")
    public ResponseEntity<?> create(@RequestBody Input input, @RequestHeader("acces-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            Id problemId = createPracticeProblemService.create(CreateProblemService.Input.builder()
                    .createCoreProblemInput(CreateProblemService.CreateCoreProblemInput.builder()
                            .name(input.createCoreProblemInput.name)
                            .description(input.createCoreProblemInput.description)
                            .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                            .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                            .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                            .author(coderId)
                            .build())
                    .categoryIds(input.categoryIds)
                    .difficulty(input.difficulty)
                    .build());
            return new ControllerHandler.Result(
                    "Success",
                    problemId
            );
        });
    }
}

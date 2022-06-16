package hanu.gdsc.practiceProblem_problem.controllers.core_problem_problem;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.services.problem.UpdateProblemService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_problem.UpdateCoreProblemProblemService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component(value = "PracticeProblem.CoreProblem.UpdateCoreProblemProblemService")
public class UpdateProblemController {
    @Autowired
    private UpdateCoreProblemProblemService updateCoreProblemProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class UpdateInput {
        public String name;
        public String description;
        public List<UpdateProblemService.UpdateMemoryLimitInput> memoryLimits;
        public List<UpdateProblemService.UpdateTimeLimitInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @PutMapping("/practiceProblem/coreProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateInput input,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            updateCoreProblemProblemService.update(UpdateCoreProblemProblemService.Input.builder()
                    .id(new Id(id))
                    .name(input.name)
                    .description(input.description)
                    .memoryLimits(input.memoryLimits)
                    .timeLimits(input.timeLimits)
                    .allowedProgrammingLanguages(input.allowedProgrammingLanguages)
                    .build());
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}


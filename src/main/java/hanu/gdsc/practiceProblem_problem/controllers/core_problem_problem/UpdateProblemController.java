package hanu.gdsc.practiceProblem_problem.controllers.core_problem_problem;

import java.util.List;

import hanu.gdsc.practiceProblem_problem.services.core_problem_problem.UpdateCoreProblemProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
@Component(value = "PracticeProblem.CoreProblem.UpdateCoreProblemProblemService")
public class UpdateProblemController {
    @Autowired
    private UpdateCoreProblemProblemService updateCoreProblemProblemService;

    public static class UpdateInput {
        public String name;
        public String description;
        public List<UpdateProblemService.UpdateMemoryLimitInput> memoryLimits;
        public List<UpdateProblemService.UpdateTimeLimitInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @PutMapping("/practiceProblem/coreProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateInput input) {
        try {
            updateCoreProblemProblemService.update(UpdateCoreProblemProblemService.Input.builder()
                    .id(new Id(id))
                    .name(input.name)
                    .description(input.description)
                    .memoryLimits(input.memoryLimits)
                    .timeLimits(input.timeLimits)
                    .allowedProgrammingLanguages(input.allowedProgrammingLanguages)
                    .build());
            return new ResponseEntity<>(
                new ResponseBody("Update successfully"), HttpStatus.OK
            );
        } catch(Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


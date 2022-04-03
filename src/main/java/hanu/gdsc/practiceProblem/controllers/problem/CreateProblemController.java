package hanu.gdsc.practiceProblem.controllers.problem;

import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.practiceProblem.services.problem.CreateProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreateProblemController {
    @Autowired
    private CreateProblemService createPracticeProblemService;

    public static class Input {
        public CreateCoreProblemInput createCoreProblemInput;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<TestCase.CreateInput> createTestCaseInputs;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;   
    }

    @PostMapping("/practiceProblem/problem")
    public ResponseEntity<?> create(@RequestBody Input input) {
        try {
            Id problemId = createPracticeProblemService.create(CreateProblemService.Input.builder()
                    .createCoreProblemInput(CreateProblemService.CreateCoreProblemInput.builder()
                            .name(input.createCoreProblemInput.name)
                            .description(input.createCoreProblemInput.description)
                            .createTestCaseInputs(input.createCoreProblemInput.createTestCaseInputs)
                            .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                            .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                            .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                            .author(Id.generateRandom()) // TODO: get from token
                            .build())
                    .categoryIds(input.categoryIds)
                    .difficulty(input.difficulty)
                    .build());
            return new ResponseEntity<>(
                    new ResponseBody("Create successfully problem", problemId), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

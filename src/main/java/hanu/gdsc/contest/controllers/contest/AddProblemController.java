package hanu.gdsc.contest.controllers.contest;

import hanu.gdsc.contest.services.contest.AddProblemService;
import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "Contest.CreateProblemController")
public class AddProblemController {
    @Autowired
    private AddProblemService addProblemService;

    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }


    public static class Input {
        public int ordinal;
        public int score;
        public CreateCoreProblemInput createCoreProblemInput;
    }


    @PostMapping("/contest/contest/{contestId}/addProblem")
    public ResponseEntity<?> addProblem(@PathVariable String contestId, @RequestBody Input input) {
        try {
            addProblemService
                    .execute(AddProblemService.Input.builder()
                            .contestId(new Id(contestId))
                            .ordinal(input.ordinal)
                            .score(input.score)
                            .createCoreProblemInput(AddProblemService.CreateCoreProblemInput.builder()
                                    .name(input.createCoreProblemInput.name)
                                    .description(input.createCoreProblemInput.description)
                                    .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                                    .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                                    .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                                    // TODO: get this id after authorize
                                    .author(Id.generateRandom())
                                    .build())
                            .build());
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

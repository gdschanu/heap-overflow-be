package hanu.gdsc.practiceProblem.controllers.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.practiceProblem.services.problem.UpdateProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
@Component(value="PracticeProblem.UpdateProblemService")
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;

    public static class UpdateInput {
        public Id coreProblemId;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    @PutMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateInput input) {
        try {
            updateProblemService.update(UpdateProblemService.Input.builder()
                    .problemId(new Id(id))
                    .coreProblemId(input.coreProblemId)
                    .categoryIds(input.categoryIds)
                    .difficulty(input.difficulty)
                    .build());
            return new ResponseEntity<>(
                new ResponseBody("Update successfully"), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

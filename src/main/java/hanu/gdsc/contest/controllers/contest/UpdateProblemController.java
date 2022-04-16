package hanu.gdsc.contest.controllers.contest;

import hanu.gdsc.contest.services.contest.UpdateProblemService;
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

@RestController
public class UpdateProblemController {
    @Autowired
    private UpdateProblemService updateProblemService;

    public static class AddProblemBody {
        public int ordinal;
        public int score;
        public Id coreProblemId;
    }


    @PostMapping("/contest/contest/{contestId}/add-problem")
    public ResponseEntity<?> addProblem(@PathVariable String contestId, @RequestBody AddProblemBody input) {
        try {
            updateProblemService
                    .addProblem(UpdateProblemService.AddProblemInput.builder()
                            .contestId(new Id(contestId))
                            .ordinal(input.ordinal)
                            .score(input.score)
                            .coreProblemId(input.coreProblemId).build());
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class RemoveProblemBody {
        public Id coreProblemId;
    }

    @PostMapping("/contest/contest/{id}/remove-problem")
    public ResponseEntity<?> removeProblem(@PathVariable String contestId, @RequestBody RemoveProblemBody input) {
        try {
            updateProblemService.removeProblem(
                    new Id(contestId),
                    input.coreProblemId
            );
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

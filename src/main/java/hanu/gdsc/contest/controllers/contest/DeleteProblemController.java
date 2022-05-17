package hanu.gdsc.contest.controllers.contest;

import hanu.gdsc.contest.services.contest.DeleteProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Contest.DeleteProblemController")
@AllArgsConstructor
public class DeleteProblemController {
    private final DeleteProblemService deleteProblemService;

    @PostMapping("/contest/contest/{contestId}/deleteProblem/{ordinal}")
    public ResponseEntity<?> execute(@PathVariable String contestId, @PathVariable int ordinal) {
        try {
            deleteProblemService.execute(
                    new Id(contestId),
                    ordinal
            );
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

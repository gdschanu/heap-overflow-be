package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.contest_contest.services.contest.JoinContestService;
import hanu.gdsc.contest_contest.services.contest.SearchContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinContestController {

    @Autowired
    private JoinContestService joinContestService;

    @PutMapping("/contest/joinContest/{coderId}/{contestId}")
    public ResponseEntity<?> joinContest(@PathVariable String coderId, @PathVariable String contestId) {
        try {
            joinContestService.joinContest(new Id(coderId), new Id(contestId));
            return new ResponseEntity<>(
                    new ResponseBody("Success"),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if(e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contest/checkIfCoderJoinContest/{coderId}")
    public ResponseEntity<?> CheckIfCoderJoinContest(@PathVariable String coderId) {
        try {
            boolean check = joinContestService.checkIfCoderJoinContest(new Id(coderId));
            return new ResponseEntity<>(
                    new ResponseBody("Success", check),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if(e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

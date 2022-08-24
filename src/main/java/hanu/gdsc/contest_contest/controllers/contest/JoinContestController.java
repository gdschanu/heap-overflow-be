package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.contest_contest.services.contest.JoinContestService;
import hanu.gdsc.contest_contest.services.contest.SearchContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JoinContestController {

    @Autowired
    private JoinContestService joinContestService;

    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/contest/{contestId}/join")
    public ResponseEntity<?> joinContest(@RequestHeader("access-token") String token, @PathVariable String contestId) {
        try {
            Id coderId = authorizeService.authorize(token);
            joinContestService.joinContest(coderId, new Id(contestId));
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

    @GetMapping("/contest/{contestId}/coderJoined")
    public ResponseEntity<?> CheckIfCoderJoinContest(@RequestHeader("access-token") String token, @PathVariable String contestId) {
        try {
            Id coderId = authorizeService.authorize(token);
            boolean check = joinContestService.checkIfCoderJoinContest(coderId, new Id(contestId));
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

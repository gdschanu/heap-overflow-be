package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.contest_contest.services.contest.SearchContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountContestController {

    @Autowired
    public SearchContestService searchContestService;

    @GetMapping("/contest/contest/countContest")
    public ResponseEntity<?> countContest() {
        try {
            return new ResponseEntity<>(new ResponseBody("Success", searchContestService.countContest()), HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            if(e.getClass().equals(BusinessLogicError.class)) {
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

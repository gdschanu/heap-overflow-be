package hanu.gdsc.contest.controllers.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.services.contest.SearchContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchContestController {
    @Autowired
    private SearchContestService searchContestService;

    @GetMapping("/contest/contest")
    public ResponseEntity<?> searchContest(@RequestParam int page,
                                           @RequestParam int perPage) {
        try {
            List<Contest> contests = searchContestService.search(page, perPage);
            return new ResponseEntity<>(
                    new ResponseBody("Success", contests),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

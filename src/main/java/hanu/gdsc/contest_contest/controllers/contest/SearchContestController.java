package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.contest_contest.services.contest.SearchContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Contest" , description = "Rest-API endpoint for Contest")
public class SearchContestController {
    @Autowired
    private SearchContestService searchContestService;

    @GetMapping("/contest")
    public ResponseEntity<?> searchContest(@RequestParam int page,
                                           @RequestParam int perPage) {
        try {
            List<SearchContestService.OutputContest> contests = searchContestService.get(page, perPage);
            return new ResponseEntity<>(
                    new ResponseBody("Success", contests),
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

    @GetMapping("/contest/{id}")
    public ResponseEntity<?> searchContest(@PathVariable String id) {
        try {
            SearchContestService.OutputContest contest = searchContestService.getById(new Id(id));
            return new ResponseEntity<>(
                    new ResponseBody("Success", contest),
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

    @GetMapping("/contest/count")
    public ResponseEntity<?> countContest() {
        try {
            return new ResponseEntity<>(new ResponseBody("Success", searchContestService.countContest()), HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            if (e.getClass().equals(BusinessLogicError.class)) {
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/contest/countParticipant/{contestId}")
    public ResponseEntity<?> countContestParticipant(@PathVariable String contestId) {
        try {
            return new ResponseEntity<>(new ResponseBody("Success", searchContestService.countContestParticipant(
                    new Id(contestId))), HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            if (e.getClass().equals(BusinessLogicError.class)) {
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

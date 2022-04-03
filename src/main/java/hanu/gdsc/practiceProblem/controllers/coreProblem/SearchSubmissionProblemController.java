package hanu.gdsc.practiceProblem.controllers.coreProblem;

import hanu.gdsc.coreProblem.services.submission.SearchSubmissionService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSubmissionProblemController {
    @Autowired
    private SearchSubmissionService searchSubmissionService;

    @GetMapping("/practiceProblem/submission")
    public ResponseEntity<?> getAllByProblemId(@RequestParam String problemId) {
        try {
//            List<Submission> submissions = searchSubmissionService.getPracticeSubmissions(new Id(problemId));
            return new ResponseEntity<>(
                    new ResponseBody("Found Successfully", null), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

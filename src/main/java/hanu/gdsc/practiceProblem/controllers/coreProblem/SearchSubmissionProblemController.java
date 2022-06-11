package hanu.gdsc.practiceProblem.controllers.coreProblem;

import hanu.gdsc.coreProblem.services.submission.SearchSubmissionService;
import hanu.gdsc.practiceProblem.services.coreProblem.submission.SearchCoreProblemSubmissionService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSubmissionProblemController {
    @Autowired
    private SearchCoreProblemSubmissionService searchCoreProblemSubmissionService;

    @GetMapping("/practiceProblem/coreProblem/submission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false) String problemId,
                                 @RequestParam(required = false) String coderId) {
        try {
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<SearchSubmissionService.Output> output = searchCoreProblemSubmissionService.get(page, perPage, problem, coder);
            return new ResponseEntity<>(
                    new ResponseBody("Found", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/practiceProblem/coreProblem/submission/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            SearchSubmissionService.Output output = searchCoreProblemSubmissionService.getById(new Id(id));
            return new ResponseEntity<>(
                new ResponseBody("Found", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

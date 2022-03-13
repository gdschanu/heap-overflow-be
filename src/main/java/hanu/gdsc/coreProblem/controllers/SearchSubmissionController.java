package hanu.gdsc.coreProblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.services.submission.SearchSubmissionService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;

@RestController
public class SearchSubmissionController {
    @Autowired
    private SearchSubmissionService searchSubmissionService;
    
    @GetMapping("/submission")
    public ResponseEntity<?> getAllByProblemId(@RequestBody String problemId) {
        try {
            List<Submission> submissions = searchSubmissionService.getAllByProblemId(new Id(problemId));
            return new ResponseEntity<>(
                new ResponseBody("Lấy ra submissions thành công", submissions), HttpStatus.OK
                );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

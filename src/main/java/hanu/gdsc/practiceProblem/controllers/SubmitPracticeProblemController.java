package hanu.gdsc.practiceProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.practiceProblem.SubmitPracticeProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class SubmitPracticeProblemController {
    @Autowired
    private SubmitPracticeProblemService submitPracticeProblemService;

    @PostMapping("/practiceProblem")
    public ResponseEntity<?> submit(@RequestBody SubmitPracticeProblemService.Input input) {
        try {
            SubmitPracticeProblemService.Output output = submitPracticeProblemService.submit(input);
            return new ResponseEntity<>(
                new ResponseBody("Submit bài thành công", output), HttpStatus.OK
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

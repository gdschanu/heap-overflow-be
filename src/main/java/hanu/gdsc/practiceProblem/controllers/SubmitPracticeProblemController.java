package hanu.gdsc.practiceProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.practiceProblem.SubmitPracticeProblemService;
import hanu.gdsc.share.controller.ResponseBody;

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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

package hanu.gdsc.practiceProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.practiceProblem.CreatePracticeProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;

@RestController
public class CreatePracticeProblemController {
    @Autowired
    private CreatePracticeProblemService createPracticeProblemService;

    @PostMapping("/practiceProblem/createPracticeProblem")
    public ResponseEntity<?> create(@RequestBody CreatePracticeProblemService.Input input) {
        try{
            Id problemId = createPracticeProblemService.create(input);
            return new ResponseEntity<>(
                new ResponseBody("Tạo bài toán thành công", problemId), HttpStatus.OK
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

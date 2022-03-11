package hanu.gdsc.practiceProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.practiceProblem.SearchPracticeProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;


@RestController
public class SearchPracticeProblemController {
    @Autowired
    private SearchPracticeProblemService servicePracticeProblemService;
    
    @GetMapping("/practiceProblem")
    public ResponseEntity<?> getById(@RequestBody String practiceProblemId){
        try {
            SearchPracticeProblemService.Output output = servicePracticeProblemService.getById(new Id(practiceProblemId));
            return new ResponseEntity<>(
                new ResponseBody("Tìm kiếm bài toán thành công", output), HttpStatus.OK
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

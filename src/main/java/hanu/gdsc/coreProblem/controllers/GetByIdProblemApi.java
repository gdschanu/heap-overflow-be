package hanu.gdsc.coreProblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import hanu.gdsc.share.controller.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.share.domains.Id;

@RestController
public class GetByIdProblemApi {
    @Autowired
    private SearchProblemService searchProblemService;

    @GetMapping("/problem")
    public ResponseEntity<?> getById(@PathVariable("uuid") String id) {
        try {
            return new ResponseEntity<>(
                searchProblemService.getById(new Id(id)),
                HttpStatus.OK
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


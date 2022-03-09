package hanu.gdsc.coreProblem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.coreProblem.services.problem.DeleteProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;

@RestController
public class DeleteProblemApi {
    @Autowired
    private DeleteProblemService deleteProblemService;

    @DeleteMapping("/problem")
    public ResponseEntity<?> deleteProblem(@RequestBody List<Id> ids){
        try{
            deleteProblemService.deleteAllById(ids);
            return new ResponseEntity<>(
                new ResponseBody("Xóa thành công"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    } 
}

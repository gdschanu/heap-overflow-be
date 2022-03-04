package hanu.gdsc.coreProblem.controllers;

import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateProblemApi {
    @Autowired
    private CreateProblemService createProblemService;

    @AllArgsConstructor
    public static class Output {
        public Id id;
    }

    @PostMapping("/problem/create")
    public ResponseEntity<?> createProblem(@RequestBody CreateProblemService.Input input) {
        try {
            Id id = createProblemService.execute(input);
            return new ResponseEntity<>(
                    new ResponseBody("Tạo Problem thành công.", new Output(id)),
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

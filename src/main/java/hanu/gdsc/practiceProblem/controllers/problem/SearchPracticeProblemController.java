package hanu.gdsc.practiceProblem.controllers.problem;

import hanu.gdsc.practiceProblem.services.problem.SearchProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchPracticeProblemController {
    @Autowired
    private SearchProblemService servicePracticeProblemService;

    @GetMapping("/practiceProblem/problem/{id}")
    public ResponseEntity<?> getById(@RequestParam String id){
        try {
            SearchProblemService.Output output = servicePracticeProblemService.getById(new Id(id));
            return new ResponseEntity<>(
                    new ResponseBody("Found Problem", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/practiceProblem/problem")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage) {
        try {
            List<SearchProblemService.Output> output = servicePracticeProblemService.get(page, perPage);
            return new ResponseEntity<>(
                    new ResponseBody("Found Problems", output), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

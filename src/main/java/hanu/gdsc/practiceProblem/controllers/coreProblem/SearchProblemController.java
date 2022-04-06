package hanu.gdsc.practiceProblem.controllers.coreProblem;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.services.coreProblem.SearchCoreProblemProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchProblemController {
    @Autowired
    private SearchCoreProblemProblemService service;

    @GetMapping("/practiceProblem/coreProblem/problem/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            SearchProblemService.Output res = service.getById(new Id(id));
            return new ResponseEntity<>(
                    new ResponseBody("Success", res), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/practiceProblem/coreProblem/problem")
    public ResponseEntity<?> getByIds(@RequestParam String ids) {
        try {
            List<Id> uids = Arrays
                    .stream(ids.split(","))
                    .map(x -> new Id(x))
                    .collect(Collectors.toList());
            List<SearchProblemService.Output> res = service.getByIds(uids);
            return new ResponseEntity<>(
                    new ResponseBody("Success", res), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

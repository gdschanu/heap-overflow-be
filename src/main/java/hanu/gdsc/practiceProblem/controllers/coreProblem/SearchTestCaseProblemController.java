package hanu.gdsc.practiceProblem.controllers.coreProblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.coreProblem.testCase.SearchCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class SearchTestCaseProblemController {
    @Autowired
    private SearchCoreProblemTestCaseService searchCoreProblemTestCaseService;

    @GetMapping("/practiceProblem/coreProblem/testCase/{problemId}/isSample")
    public ResponseEntity<?> getSampleTestCases(@PathVariable String problemId) {
        try {
            List<SearchCoreProblemTestCaseService.Output> outputs = searchCoreProblemTestCaseService.getSampleTestCases(new hanu.gdsc.share.domains.Id(problemId));
            return new ResponseEntity<>(
                new ResponseBody("Get Successfully", outputs), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package hanu.gdsc.practiceProblemSubdomain.problemContext.controllers.coreProblem;

import hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.testCase.DeleteCoreProblemTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class DeleteTestCaseProblemController {
    @Autowired
    private DeleteCoreProblemTestCaseService deleteCoreProblemTestCaseService;

    @DeleteMapping("/practiceProblem/coreProblem/testCase/{id}")
    public ResponseEntity<?> deleteByIds(@PathVariable String id) {
        try {
            deleteCoreProblemTestCaseService.deleteById(new Id(id));
            return new ResponseEntity<>(
                new ResponseBody("delete successfully"), HttpStatus.OK
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

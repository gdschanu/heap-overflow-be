package hanu.gdsc.practiceProblem_problem.controllers.core_problem_testCase;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problem.services.core_problem_testCase.DeleteCoreProblemTestCaseService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTestCaseProblemController {
    @Autowired
    private DeleteCoreProblemTestCaseService deleteCoreProblemTestCaseService;
    @Autowired
    private AuthorizeService authorizeService;


    @DeleteMapping("/practiceProblem/coreProblem/testCase/{id}")
    public ResponseEntity<?> deleteByIds(@PathVariable String id, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            authorizeService.authorize(token);
            deleteCoreProblemTestCaseService.deleteById(new Id(id));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

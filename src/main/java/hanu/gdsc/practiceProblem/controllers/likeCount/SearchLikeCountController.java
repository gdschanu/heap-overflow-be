package hanu.gdsc.practiceProblem.controllers.likeCount;

import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.practiceProblem.services.likeCount.SearchLikeCountService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchLikeCountController {
    @Autowired
    private SearchLikeCountService searchLikeCountService;

    @GetMapping("/practiceProblem/likeCount/{problemId}")
    public ResponseEntity<?> getByProblemId(@PathVariable String problemId) {
        try {
            LikeCount cnt = searchLikeCountService.getByProblemId(
                    new Id(problemId)
            );
            return new ResponseEntity<>(
                    new ResponseBody("Success", cnt), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/practiceProblem/likeCount")
    public ResponseEntity<?> getByProblemIds(@RequestParam String problemIds) {
        try {
            List<String> ids = List.of(problemIds.split(","));
            List<LikeCount> cnt = searchLikeCountService
                    .getByProblemIds(ids.stream().map(x -> new Id(x))
                            .collect(Collectors.toList()));
            return new ResponseEntity<>(
                    new ResponseBody("Success", cnt), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

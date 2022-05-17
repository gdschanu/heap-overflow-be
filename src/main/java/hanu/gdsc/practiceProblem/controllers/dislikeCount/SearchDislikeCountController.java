package hanu.gdsc.practiceProblem.controllers.dislikeCount;

import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.practiceProblem.services.dislikeCount.SearchDislikeCountService;
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
public class SearchDislikeCountController {
    @Autowired
    private SearchDislikeCountService searchDislikeCountService;

    @GetMapping("/practiceProblem/dislikeCount/{problemId}")
    public ResponseEntity<?> getByProblemId(@PathVariable String problemId) {
        try {
            DislikeCount dislikeCount = searchDislikeCountService.getByProblemId(
                    new Id(problemId)
            );
            return new ResponseEntity<>(
                    new ResponseBody("Success", dislikeCount), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/practiceProblem/dislikeCount")
    public ResponseEntity<?> getByProblemIds(@RequestParam String problemIds) {
        try {
            List<String> ids = List.of(problemIds.split(","));
            List<DislikeCount> dislikeCounts = searchDislikeCountService
                    .getByProblemIds(ids.stream().map(x -> new Id(x))
                            .collect(Collectors.toList()));
            return new ResponseEntity<>(
                    new ResponseBody("Success", dislikeCounts), HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

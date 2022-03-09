package hanu.gdsc.coreProblem.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.share.controller.ResponseBody;
import lombok.Builder;

@RestController
public class SearchProblemApi {
    @Autowired
    private SearchProblemService searchProblemService;

    @Builder
    public static class Output {
        public List<Problem> problems = new ArrayList<>();
        public int page;
        public int totalPages;
    }
    
    @GetMapping("/problem")
    public ResponseEntity<?> searchProblem(@RequestParam int page, @RequestParam int limitPerPage) {
        Pageable pageable = PageRequest.of(page - 1, limitPerPage);
        try {
            return new ResponseEntity<>(
                Output.builder()
                .problems(searchProblemService.search(pageable))
                .page(page)
                .totalPages((int) Math.ceil((double) searchProblemService.count() / limitPerPage)),
                HttpStatus.OK); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        
    }
}

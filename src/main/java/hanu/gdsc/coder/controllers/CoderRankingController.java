package hanu.gdsc.coder.controllers;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.services.CoderRankingService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Coder" , description = "Rest-API endpoint for Coder")
public class CoderRankingController {

    @Autowired
    public CoderRankingService coderRankingService;

    @GetMapping("/coder/rank")
    public ResponseEntity<?> coderRanking() {
        try {
            List<Coder> sortedRankingCoders = coderRankingService.getAllCoderRankingSorted();
            return new ResponseEntity<>(new ResponseBody("Success", sortedRankingCoders), HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            if(e.getClass().equals(BusinessLogicError.class)) {
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

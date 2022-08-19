package hanu.gdsc.coder.controllers;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.services.GetTopCodersService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Coder" , description = "Rest-API endpoint for Coder")
public class GetTopCodersController {

    @Autowired
    private GetTopCodersService getTopCodersService;

    @GetMapping("/coder/top")
    public ResponseEntity<?> getTopCoder(@RequestParam int num) {
        try {
            List<Coder> topCoders = getTopCodersService.getTopCoders(num);
            return new ResponseEntity<>(new ResponseBody("Success", topCoders),
                    HttpStatus.OK);
        } catch (Throwable e) {
            e.printStackTrace();
            if(e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

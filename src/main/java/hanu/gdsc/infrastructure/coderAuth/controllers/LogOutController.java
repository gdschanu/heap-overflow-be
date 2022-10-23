package hanu.gdsc.infrastructure.coderAuth.controllers;

import hanu.gdsc.domain.coderAuth.services.LogOutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class LogOutController {
    
    @Autowired
    private LogOutService logOutService;

    @PostMapping("/coderAuth/logOut")
    public ResponseEntity<?> logOut(@RequestHeader String token) {
        try {
            logOutService.logOut(token);
            return new ResponseEntity<>(
            new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicException.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}

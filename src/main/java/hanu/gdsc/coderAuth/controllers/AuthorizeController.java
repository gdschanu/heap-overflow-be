package hanu.gdsc.coderAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@Controller
public class AuthorizeController {
    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/coderAuth/authorize")
    public ResponseEntity<?> authorize(@RequestHeader String token) {
        try {
            authorizeService.authorize(token);
            return new ResponseEntity<>(
                new ResponseBody("Authorize thành công"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

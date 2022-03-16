package hanu.gdsc.coderAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.services.SignUpService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            boolean signUpSuccess = signUpService.signUp(user.getEmail(),
            user.getUsername(), user.getPassword());
            return new ResponseEntity<>(
                    new ResponseBody("Đăng kí thành công.",signUpSuccess),
                    HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

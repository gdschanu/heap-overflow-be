package hanu.gdsc.coderAuth.controllers;

import hanu.gdsc.coderAuth.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.coderAuth.services.SignUpService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private LogInService logInService;

    public static class Input {
        public String email;
        public String username;
        public String password;
    }

    @PostMapping("/coderAuth/signUp")
    public ResponseEntity<?> signUp(@RequestBody Input input) {
        try {
            signUpService.signUpService(input.email, input.username, input.password);
            LogInService.Output output = logInService.logInService(input.email, input.password);
            return new ResponseEntity<>(
                    new ResponseBody("Success", output), HttpStatus.OK);
        } catch (Throwable e) {
            if(e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

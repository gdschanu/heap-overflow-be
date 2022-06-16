package hanu.gdsc.coderAuth_coderAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.coderAuth_coderAuth.services.ConfirmForgotPasswordService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;

@Controller
public class ConfirmForgotPasswordController {
    @Autowired
    private ConfirmForgotPasswordService confirmForgotPasswordService;

    public static class Input {
        public String email;
        public String code;
        public String newPassword;
    }

    @PutMapping("/coderAuth/confirmForgotPassword")
    public ResponseEntity<?> confirmForgotPassword(@RequestBody Input input) {
        try {
            confirmForgotPasswordService.confirmForgotPassword(input.email, input.code, input.newPassword);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

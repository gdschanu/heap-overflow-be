package hanu.gdsc.coderAuth.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.coderAuth.services.ForgotPasswordService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.exceptions.BusinessLogicException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Schema(title = "Forgot Password", description = "Data transfer object to change password")
    public static class InputForgotPass{
        @Schema(description = "specify the email of user to change password", example = "hihi@gmail.com", required = true)
        public String email;
    }

    @PostMapping("/coderAuth/password/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody InputForgotPass input) {
        try {
            forgotPasswordService.forgotPassword(input.email);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicException.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

package hanu.gdsc.infrastructure.coderAuth.controllers;

import hanu.gdsc.domain.coderAuth.services.LogInService;
import hanu.gdsc.domain.coderAuth.services.SignUpService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private LogInService logInService;

    @Schema(title = "sign up", description = "Data transfer object for coder auth to sign up")
    public static class InputSignUp {
        @Schema(description = "specify the email of user", example = "hihi@gmail.com", required = true)
        public String email;
        @Schema(description = "specify the username of user", example = "DatKhoaiTo", required = true)
        public String username;
        @Schema(description = "specify the password of contest", example = "*********", required = true)
        public String password;
    }

    @PostMapping("/coderAuth/signUp")
    public ResponseEntity<?> signUp(@RequestBody InputSignUp input) {
        try {
            signUpService.signUpService(input.email, input.username, input.password);
            LogInService.Output output = logInService.logInService(input.email, input.password);
            return new ResponseEntity<>(
                    new ResponseBody("Success", output), HttpStatus.OK);
        } catch (Throwable e) {
            if(e instanceof BusinessLogicException) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

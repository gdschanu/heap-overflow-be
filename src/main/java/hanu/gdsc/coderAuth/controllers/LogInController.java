package hanu.gdsc.coderAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.services.LogInService;
import hanu.gdsc.share.controller.ResponseBody;

@Controller
public class LogInController {
    @Autowired
    private LogInService logInService;

    @GetMapping("/login")
    public ResponseEntity<?> logIn(@RequestParam String usernameOrEmail,
    @RequestParam String password) {
        try {
            String token;
            if(Email.isValidEmail(usernameOrEmail)) {
                token = logInService.checkUserInformation(new Email(usernameOrEmail),
             new Password(password));
            } else{
                token = logInService.checkUserInformation(new Username(usernameOrEmail),
             new Password(password));
            }
            return new ResponseEntity<>(
                new ResponseBody("Đăng nhập thành công.",token),
                HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

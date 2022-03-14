package hanu.gdsc.coderAuth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.services.LogInService;
import hanu.gdsc.share.controller.ResponseBody;

@Controller
public class LogInController {
    @Autowired
    private LogInService logInService;

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) {
        try {
            String token = logInService.checkUserInformation(logInService.usernameOrEmail(user), 
            user.getPassword());
            return new ResponseEntity<>(
                    new ResponseBody("Đăng nhập thành công.", token),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

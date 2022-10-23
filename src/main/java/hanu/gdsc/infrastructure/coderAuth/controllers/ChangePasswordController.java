package hanu.gdsc.infrastructure.coderAuth.controllers;

import hanu.gdsc.domain.coderAuth.services.ChangePasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class ChangePasswordController {
    @Autowired
    private ChangePasswordService changePasswordService;

    public static class Input {
        public String oldPassword;
        public String newPassword;
    }

    @PutMapping("/coderAuth/password")
    public ResponseEntity<?> changePassword(@RequestHeader String token, @RequestBody Input input) {
        try {
            changePasswordService.changePassword(token, input.oldPassword, input.newPassword);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicException.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

package hanu.gdsc.coderAuth_coderAuth.controllers;

import hanu.gdsc.coderAuth_coderAuth.services.SendRegisterVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import hanu.gdsc.coderAuth_coderAuth.services.AuthorizeService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.error.UnauthorizedError;

@Controller
public class SendRegisterVerificationCodeController {
    @Autowired
    private SendRegisterVerificationCodeService sendRegisterVerificationCodeService;

    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/coderAuth/sendRegisterVerificationCode")
    public ResponseEntity<?> sendRegisterVerificationCode(@RequestHeader String token) {
        try {
            Id coderId = authorizeService.authorize(token);
            sendRegisterVerificationCodeService.sendRegisterVerificationCodeService(coderId);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                // e.printStackTrace();
                if (e.getClass().equals(UnauthorizedError.class)) {
                    return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.UNAUTHORIZED);
                }
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null),
                        HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

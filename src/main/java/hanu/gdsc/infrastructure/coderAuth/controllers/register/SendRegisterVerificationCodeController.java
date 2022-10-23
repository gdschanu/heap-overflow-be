package hanu.gdsc.infrastructure.coderAuth.controllers.register;

import hanu.gdsc.domain.coderAuth.services.register.SendRegisterVerificationCodeService;
import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class SendRegisterVerificationCodeController {
    @Autowired
    private SendRegisterVerificationCodeService sendRegisterVerificationCodeService;

    @Autowired
    private AuthorizeService authorizeService;

    @PostMapping("/coderAuth/registerVerificationCode/send")
    public ResponseEntity<?> sendRegisterVerificationCode(@RequestHeader String token) {
        try {
            Id coderId = authorizeService.authorize(token);
            sendRegisterVerificationCodeService.sendRegisterVerificationCodeService(coderId);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if (e instanceof BusinessLogicException) {
                // e.printStackTrace();
                if (e.getClass().equals(UnauthorizedException.class)) {
                    return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.UNAUTHORIZED);
                }
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null),
                        HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

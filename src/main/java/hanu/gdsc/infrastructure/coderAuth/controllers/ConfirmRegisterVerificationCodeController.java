package hanu.gdsc.infrastructure.coderAuth.controllers;

import hanu.gdsc.domain.coderAuth.services.AuthorizeService;
import hanu.gdsc.domain.coderAuth.services.ConfirmRegisterVerificationCodeService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder Auth" , description = "Rest-API endpoint for Coder Auth")
public class ConfirmRegisterVerificationCodeController {
    @Autowired
    private ConfirmRegisterVerificationCodeService confirmRegisterVerificationCodeService;

    @Autowired
    private AuthorizeService authorizeService;

    @Schema(title = "Confirm", description = "Data transfer object for Contest to create")
    public static class InputConfirmRegister{
        @Schema(description = "specify the code to confirm register", example = "iloveu3000", required = true)
        public String code;
    }

    @PostMapping("/coderAuth/registerVerificationCode/confirm")
    public ResponseEntity<?> confirmRegisterVerificationCode(@RequestBody InputConfirmRegister input, @RequestHeader String token) {
        try {
            Id coderId = authorizeService.authorize(token);
            confirmRegisterVerificationCodeService.confirmRegisterVerificationCode(input.code, coderId);
            return new ResponseEntity<>(new ResponseBody("Success"),
            HttpStatus.OK);
        } catch (Throwable e) {
            if(e instanceof BusinessLogicException) {
                e.printStackTrace();
                if(e.getClass().equals(UnauthorizedException.class)) {
                    return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.UNAUTHORIZED);
                }
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

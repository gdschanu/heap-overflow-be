package hanu.gdsc.infrastructure.coderAuth.controllers.password;

import hanu.gdsc.domain.coderAuth.services.password.ConfirmForgotPasswordService;
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
public class ConfirmForgotPasswordController {
    @Autowired
    private ConfirmForgotPasswordService confirmForgotPasswordService;

    @Schema(title = "Confirm Forgot Password", description = "Data transfer object to change password")
    public static class InputConfirmForgotPass {
        @Schema(description = "specify the email of user to confirm forgot", example = "hihi@gmail.com ", required = true)
        public String email;
        @Schema(description = "specify the code to confirm forgot", example = "iloveu3000", required = true)
        public String code;
        @Schema(description = "specify the new password of user to finished change password", example = "********", required = true)
        public String newPassword;
    }

    @PostMapping("/coderAuth/password/confirmForgotten")
    public ResponseEntity<?> confirmForgotPassword(@RequestBody InputConfirmForgotPass input) {
        try {
            confirmForgotPasswordService.confirmForgotPassword(input.email, input.code, input.newPassword);
            return new ResponseEntity<>(new ResponseBody("Success"), HttpStatus.OK);
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicException.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

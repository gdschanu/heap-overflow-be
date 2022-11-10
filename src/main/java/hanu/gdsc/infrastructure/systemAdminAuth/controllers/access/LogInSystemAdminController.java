package hanu.gdsc.infrastructure.systemAdminAuth.controllers.access;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import hanu.gdsc.domain.systemAdminAuth.services.access.LogInSystemAdminService;
import hanu.gdsc.infrastructure.share.controller.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "System Admin Auth" , description = "Rest-API endpoint for System Admin Auth")
public class LogInSystemAdminController {
    @Autowired
    private LogInSystemAdminService logInService;

    @Schema(title = "LogIn", description = "Data transfer object to log in")
    public static class InputLogIn {
        @Schema(description = "specify the email or username of user to log in", example = "hihi@gmail.com", required = true)
        public String usernameOrEmail;
        @Schema(description = "specify the password to log in", example = "*********", required = true)
        public String password;
    }

    @PostMapping("/systemAdminAuth/logIn")
    public ResponseEntity<?> logIn(@RequestBody InputLogIn input) {
        try {
            LogInSystemAdminService.Output output = logInService.logInService(input.usernameOrEmail, input.password);
            return new ResponseEntity<>(
                    new ResponseBody("Success", output),
                    HttpStatus.OK);
        } catch (Throwable e) {
            if(e instanceof BusinessLogicException) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

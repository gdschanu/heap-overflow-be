package hanu.gdsc.coderAuth.controllers;

import hanu.gdsc.coder.services.GetCoderInfoService;
import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.coderAuth.services.GetCoderAccountService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "CoderAuth" , description = "Rest-API endpoint for CoderAuth")
public class GetCoderAccountController {

    @Autowired
    private GetCoderAccountService getCoderService;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/coderAuth")
    public ResponseEntity<?> getAccount(@RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            GetCoderAccountService.OutputAccount output = getCoderService.getCoderAccount(coderId);
            return new ControllerHandler.Result("Success", output);
        });
    }
}

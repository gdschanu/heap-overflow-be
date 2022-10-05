package hanu.gdsc.coder.controllers;

import hanu.gdsc.coder.services.GetCoderService;
import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Coder" , description = "Rest-API endpoint for Coder")
public class GetCoderInfoController {

    @Autowired
    private GetCoderService getCoderService;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/coder/{coderId}")
    public ResponseEntity<?> getCoderInfo(@PathVariable String coderId) {
        return ControllerHandler.handle(() -> {
            GetCoderService.OutputInfo output = getCoderService.getCoderInfo(new Id(coderId));
            return new ControllerHandler.Result("Success", output);
        });
    }

    @GetMapping("/coderAuth")
    public ResponseEntity<?> getAccount(@RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            GetCoderService.OutputAccount output = getCoderService.getCoderAccount(coderId);
            return new ControllerHandler.Result("Success", output);
        });
    }
}

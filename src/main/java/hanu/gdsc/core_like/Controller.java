package hanu.gdsc.core_like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.core_like.services.reactedObject.ReactService;
import hanu.gdsc.share.controller.ControllerHandler;

@RestController
public class Controller {
    @Autowired
    private ReactService reactService;
    
    @PostMapping("/corelike/react")
    public ResponseEntity<?> react(@RequestBody ReactService.Input input) {
        return ControllerHandler.handle(() -> {
            boolean react = reactService.react(input);
            return new ControllerHandler.Result(
                "Success",
                react
            );
        });
    }
}

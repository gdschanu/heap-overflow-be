package hanu.gdsc.infrastructure.coder.controllers;

import hanu.gdsc.domain.coder.models.Coder;
import hanu.gdsc.domain.coder.services.SearchCoderService;
import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Coder", description = "Rest-API endpoint for Coder")
public class SearchCoderController {

    @Autowired
    private SearchCoderService getCoderService;

    @Autowired
    private AuthorizeService authorizeService;

    @GetMapping("/coder/{coderId}")
    public ResponseEntity<?> getCoderInfo(@PathVariable String coderId) {
        return ControllerHandler.handle(() -> {
            Coder output = getCoderService.getById(new Id(coderId));
            return new ControllerHandler.Result("Success", output);
        });
    }

    @GetMapping("/coder/top")
    public ResponseEntity<?> getTopCoder(@RequestParam int page,
                                         @RequestParam int perPage) {
        return ControllerHandler.handle(() -> {
                    List<Coder> topCoders = getCoderService.getTopCoders(page, perPage);
                    return new ControllerHandler.Result("Success", topCoders);
                }
        );
    }
}

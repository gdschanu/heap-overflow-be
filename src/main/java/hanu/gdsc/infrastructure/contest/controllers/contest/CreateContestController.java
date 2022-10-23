package hanu.gdsc.infrastructure.contest.controllers.contest;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.domain.contest.services.contest.CreateContestService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Contest" , description = "Rest-API endpoint for Contest")
public class CreateContestController {
    @Autowired
    private CreateContestService createContestService;
    @Autowired
    private AuthorizeService authorizeService;

    @Schema(title = "Create contest", description = "Data transfer object for Contest to create" )
    public static class InputCreateContest {
        @Schema(description = "specify the name of contest", example = "contest 2020", required = true)
        public String name;
        @Schema(description = "specify the description of contest", example = "blablalbalbalba", required = true)
        public String description;
        @Schema(description = "specify the start date of contest", example = "1/1/2022", required = true)
        public String startAt;
        @Schema(description = "specify the end date of contest", example = "10/1/2022", required = true)
        public String endAt;
        @Schema(description = "specify the input to create problem of contest", required = true)
        public List<CreateContestService.CreateProblemInput> problems;
    }

    @PostMapping("/contest/")
    public ResponseEntity<?> createContest(@RequestBody CreateContestController.InputCreateContest input, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            Id id = createContestService.create(new CreateContestService.Input(
                    input.name,
                    input.description,
                    new DateTime(input.startAt),
                    new DateTime(input.endAt),
                    coderId,
                    input.problems
            ));
            return new ControllerHandler.Result(
                    "Success",
                    id
            );
        });
    }
}

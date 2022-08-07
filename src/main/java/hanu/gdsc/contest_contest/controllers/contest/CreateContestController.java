package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.contest_contest.services.contest.CreateContestService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreateContestController {
    @Autowired
    private CreateContestService createContestService;
    @Autowired
    private AuthorizeService authorizeService;

    public static class Input {
        public String name;
        public String description;
        public String startAt;
        public String endAt;
        private List<CreateContestService.CreateProblemInput> problems;
    }

    @PostMapping("/contest/contest")
    public ResponseEntity<?> createContest(@RequestBody Input input, @RequestHeader("access-token") String token) {
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

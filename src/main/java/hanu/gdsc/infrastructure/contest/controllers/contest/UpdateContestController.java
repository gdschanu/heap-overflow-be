package hanu.gdsc.infrastructure.contest.controllers.contest;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.domain.contest.services.contest.UpdateContestService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UpdateContestController {

    private final UpdateContestService updateContestService;

    private final AuthorizeService authorizeService;
    public static class Input {
        public String name;
        public String description;
        public String startAt;
        public String endAt;
        public List<UpdateContestService.UpdateProblemInput> problems;
    }

    @PutMapping("/contest/{contestId}")
    public ResponseEntity<?> updateContestService(@RequestBody Input input, @PathVariable String contestId, @RequestHeader("access-token") String token) throws InvalidInputException, UnauthorizedException {
        Id coderId = authorizeService.authorize(token);
        return ControllerHandler.handle(()-> {
            updateContestService.execute(
                    new UpdateContestService.Input(
                            new Id(contestId),
                            input.name,
                            input.description,
                            new DateTime(input.startAt),
                            new DateTime(input.endAt),
                            input.problems,
                            coderId
                    )
            );
            return new ControllerHandler.Result("Success", null);
        });
    }
}

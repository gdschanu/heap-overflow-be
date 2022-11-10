package hanu.gdsc.infrastructure.contest.controllers.contest;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.domain.contest.services.contest.SubmitContestProblemService;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.services.submit.SubmitService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Contest" , description = "Rest-API endpoint for Contest")
public class SubmitContestProblemController {
    @Autowired
    private SubmitContestProblemService submitContestProblemService;
    @Autowired
    private AuthorizeService authorizeService;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @PostMapping("/contest/{contestId}/problem/{contestProblemOrdinal}/submit")
    public ResponseEntity<?> submit(@RequestBody Input input,
                                    @PathVariable("contestId") String contestId,
                                    @PathVariable("contestProblemOrdinal") int contestProblemOrdinal,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            SubmitService.Output output = submitContestProblemService
                    .execute(new SubmitContestProblemService.Input(
                            contestProblemOrdinal,
                            new Id(contestId),
                            coderId,
                            input.code,
                            input.programmingLanguage
                    ));
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }
}

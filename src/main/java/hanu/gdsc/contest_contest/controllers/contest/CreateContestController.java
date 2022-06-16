package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.contest_contest.services.contest.CreateContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateContestController {
    @Autowired
    private CreateContestService createContestService;

    public static class Input {
        public String name;
        public String description;
        public String startAt;
        public String endAt;
    }

    @PostMapping("/contest/contest")
    public ResponseEntity<?> createContest(@RequestBody Input input) {
        try {
            Id id = createContestService.create(CreateContestService.Input.builder()
                    .name(input.name)
                    .description(input.description)
                    .startAt(new DateTime(input.startAt))
                    .endAt(new DateTime(input.endAt))
                    // TODO: set createdBy = coderId after authorzing
                    .createdBy(Id.generateRandom())
                    .build());
            return new ResponseEntity<>(
                    new ResponseBody("Success", id),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

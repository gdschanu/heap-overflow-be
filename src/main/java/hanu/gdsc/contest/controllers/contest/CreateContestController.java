package hanu.gdsc.contest.controllers.contest;

import hanu.gdsc.contest.services.contest.CreateContestService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CreateContestController {
    private CreateContestService createContestService;

    @AllArgsConstructor
    public static class Output {
        public Id id;
    }

    @PostMapping("/contest")
    public ResponseEntity<?> createContest(@RequestBody CreateContestService.Input input) {
        try {
            Id id = createContestService.create(input);
            return new ResponseEntity<>(
                    new ResponseBody("Tạo kì thi thành công.", new Output(id)),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if(e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
}

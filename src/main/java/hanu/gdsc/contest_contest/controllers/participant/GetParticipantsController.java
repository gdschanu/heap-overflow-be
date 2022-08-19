package hanu.gdsc.contest_contest.controllers.participant;

import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.services.participant.GetParticipantsService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetParticipantsController {

    @Autowired
    private GetParticipantsService getParticipantsService;

    @GetMapping("/participant")
    public ResponseEntity<?> searchContest() {
        try {
            List<Participant> participants = getParticipantsService.getParticipants();
            return new ResponseEntity<>(
                    new ResponseBody("Success", participants),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if(e instanceof BusinessLogicError) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

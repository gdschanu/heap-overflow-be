package hanu.gdsc.contest.controllers.participant;

import hanu.gdsc.contest.services.participant.SearchParticipantService;

import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Contest - Participant", description = "Rest-API endpoint for Contest Participant")
public class SearchParticipantsController {
    @Autowired
    private SearchParticipantService searchParticipantService;

    @GetMapping("/participant/{contestId}")
    public ResponseEntity<?> searchContest(@PathVariable String contestId, @RequestParam int page, @RequestParam int perPage) {
        return ControllerHandler.handle(() -> {
            List<SearchParticipantService.OutputParticipant> outputParticipants = searchParticipantService.searchByContestId(new Id(contestId), page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    outputParticipants
            );
        });
    }

}

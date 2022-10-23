package hanu.gdsc.infrastructure.contest.controllers.participant;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.domain.contest.services.participant.SearchParticipantService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Contest - Participant", description = "Rest-API endpoint for Contest Participant")
public class SearchParticipantsController {
    @Autowired
    private SearchParticipantService searchParticipantService;
    @Autowired
    private AuthorizeService authorizeService;


    @GetMapping("/contest/{contestId}/participant")
    public ResponseEntity<?> searchContest(@PathVariable String contestId, @RequestParam int page, @RequestParam int perPage) {
        return ControllerHandler.handle(() -> {
            List<SearchParticipantService.OutputParticipant> outputParticipants = searchParticipantService.searchByContestId(new Id(contestId), page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    outputParticipants
            );
        });
    }

    @GetMapping("/contest/{contestId}/participant/count")
    public ResponseEntity<?> countContestParticipant(@PathVariable String contestId) {
        return ControllerHandler.handle(() -> {
            return new ControllerHandler.Result(
                    "Success",
                    searchParticipantService.countContestParticipant(new Id(contestId))
            );
        });
    }

    @Operation(
            summary = "check coder joined contest or not",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Joined",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "false")
                            )
                    }
            ), @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content()}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "invalid request",
                    content = {@Content()}
            )}
    )
    @GetMapping("/contest/{contestId}/participant/joined")
    public ResponseEntity<?> joinedContest(@RequestHeader("access-token") String token, @PathVariable String contestId) {
        return ControllerHandler.handle(() -> {
            //should return page
            Id coderId = authorizeService.authorize(token);
            boolean check = searchParticipantService.joinedContest(coderId, new Id(contestId));
            return new ControllerHandler.Result(
                    "Success",
                    check
            );
        });
    }
}

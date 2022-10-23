package hanu.gdsc.infrastructure.contest.controllers.participant;

import hanu.gdsc.domain.coderAuth.services.access.AuthorizeService;
import hanu.gdsc.domain.contest.services.participant.CreateParticipantService;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.domain.share.models.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@AllArgsConstructor
public class CreateParticipantController {
    private final AuthorizeService authorizeService;
    private final CreateParticipantService createParticipantService;

    @Operation(
            summary = "join contest",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully created. Coder joined this contest.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Success")
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
    @PostMapping("/contest/{contestId}/participant")
    public ResponseEntity<?> joinContest(@PathVariable String contestId, @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            createParticipantService.execute(coderId, new Id(contestId));
            return new ControllerHandler.Result(
                    "Success",
                    null
            );
        });
    }
}

package hanu.gdsc.practiceProblem_problemDiscussion.controllers.post;

import hanu.gdsc.practiceProblem_problemDiscussion.services.post.CreatePostService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Practice Problem-Discussion Post" , description = "Rest-API endpoint for Practice Problem")
public class CreatePostController {
    private final CreatePostService createPostService;

    @Operation(
            summary = "Create the discussion of practice problem",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully created.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "62aeff0d9081bab25998b0d1")
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
    @PostMapping("practiceProblem/post")
    public ResponseEntity<?> create(@RequestBody CreatePostService.InputCreatePost input) {
        return ControllerHandler.handle(() -> {
            Id postId = createPostService.execute(input);
            return new ControllerHandler.Result(
                    "Success",
                    postId
            );
        });
    }
}

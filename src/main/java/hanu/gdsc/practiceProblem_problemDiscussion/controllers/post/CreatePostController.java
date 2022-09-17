package hanu.gdsc.practiceProblem_problemDiscussion.controllers.post;

import hanu.gdsc.coderAuth.services.AuthorizeService;
import hanu.gdsc.practiceProblem_problemDiscussion.services.post.CreatePostService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Practice Problem - Discussion Post", description = "Rest-API endpoint for Practice Problem")
public class CreatePostController {
    private final CreatePostService createPostService;
    private final AuthorizeService authorizeService;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "create", description = "Data transfer object for Discussion to create")
    public static class InputCreatePost {
        @Schema(description = "specify title of the discussion want to create post", example = "how to solve this problem with java", required = true)
        public String title;
        @Schema(description = "specify the content of the discussion to create post", example = "blalalblablablablalbalbalba", required = true)
        public String content;
    }

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
    @PostMapping("practiceProblem/{problemId}/post")
    public ResponseEntity<?> create(@RequestBody InputCreatePost input,
                                    @PathVariable("problemId") String problemId,
                                    @RequestHeader("access-token") String token) {
        return ControllerHandler.handle(() -> {
            Id coderId = authorizeService.authorize(token);
            Id postId = createPostService.execute(new CreatePostService.Input(
                    new Id(problemId),
                    input.title,
                    coderId,
                    input.content
            ));
            return new ControllerHandler.Result(
                    "Success",
                    postId
            );
        });
    }
}

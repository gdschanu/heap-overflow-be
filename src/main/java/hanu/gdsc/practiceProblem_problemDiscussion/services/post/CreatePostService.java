package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component(value = "PracticeProblem.CreatePostService")
public class CreatePostService {
    private final hanu.gdsc.core_discussion.services.post.CreatePostService createCoreDiscussionPostService;
    private final SearchProblemService searchProblemService;
    private final PostRepository postRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "create", description = "Data transfer object for Discussion to create")
    public static class InputCreatePost {
        @Schema(description = "specify Id of the problem want to create post", example = "62aeff0d9081bab25998b0d1", required = true)
        public Id problemId;
        @Schema(description = "specify title of the discussion want to create post", example = "how to solve this problem with java", required = true)
        public String title;
        @Schema(description = "specify the Id of author who are create post", example = "62aeff0d9081bab25998b0d2", required = true)
        public Id author;
        @Schema(description = "specify the content of the discussion to create post", example = "blalalblablablablalbalbalba", required = true)
        public String content;
    }

    public Id execute(InputCreatePost input) {
        try {
            searchProblemService.getById(input.problemId);
        } catch (NotFoundError error) {
            throw new NotFoundError("Unknown problem, cannot create post for an unexist problem");
        }
        Id corePostId = createCoreDiscussionPostService.execute(new hanu.gdsc.core_discussion.services.post.CreatePostService .Input(
                input.title,
                input.author,
                input.content,
                ServiceName.serviceName
        ));
        Post post = Post.create(input.problemId, corePostId);
        postRepository.save(post);
        return post.getId();
    }
}

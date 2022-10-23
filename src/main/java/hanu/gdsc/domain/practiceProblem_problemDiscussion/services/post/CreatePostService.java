package hanu.gdsc.domain.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.domain.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.repositories.PostRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.models.Post;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Component(value = "PracticeProblem.CreatePostService")
public class CreatePostService {
    private final hanu.gdsc.domain.core_discussion.services.post.CreatePostService createCoreDiscussionPostService;
    private final SearchProblemService searchProblemService;
    private final PostRepository postRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "create", description = "Data transfer object for Discussion to create")
    public static class Input {
        public Id problemId;
        public String title;
        public Id author;
        public String content;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Id execute(Input input) throws NotFoundException {
        try {
            searchProblemService.getById(input.problemId);
        } catch (NotFoundException e) {
            throw new NotFoundException("Unknown problem, cannot create post for an unexist problem");
        }
        Id corePostId = createCoreDiscussionPostService.execute(new hanu.gdsc.domain.core_discussion.services.post.CreatePostService.Input(
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

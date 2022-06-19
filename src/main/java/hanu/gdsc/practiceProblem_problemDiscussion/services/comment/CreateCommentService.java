package hanu.gdsc.practiceProblem_problemDiscussion.services.comment;

import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.practiceProblem_problemDiscussion.services.core_comment.CreateCoreCommentService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CreateCommentService {
    private PostRepository postRepository;
    private CreateCoreCommentService createCoreCommentService;

    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id postId;
        private Id author;
        private String content;
        private Id parentCommentId;
    }

    public Id execute(Input input) {
        Post post = postRepository.getById(input.postId);
        if (post == null)
            throw new NotFoundError("Unknown post");
        return createCoreCommentService.create(new CreateCoreCommentService.Input(
                input.author,
                input.content,
                input.parentCommentId,
                post.getCorePostId()
        ));
    }
}

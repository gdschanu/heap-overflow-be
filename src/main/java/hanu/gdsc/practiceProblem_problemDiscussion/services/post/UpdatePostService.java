package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.practiceProblem_problemDiscussion.services.core_post.UpdateCorePostService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UpdatePostService {
    private final UpdateCorePostService updateCorePostService;
    private final PostRepository postRepository;

    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id id;
        private String title;
        private String content;
    }

    public void execute(Input input) {
        Post post = postRepository.getById(input.id);
        if (post == null)
            throw new NotFoundError("Unknown post");
        updateCorePostService.execute(new UpdateCorePostService.Input(
                post.getCorePostId(),
                input.title,
                input.content
        ));
    }
}

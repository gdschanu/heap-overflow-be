package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UpdatePostService {
    private final hanu.gdsc.core_discussion.services.post.UpdatePostService updateCoreDiscussionPostService;
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
        updateCoreDiscussionPostService.execute(new hanu.gdsc.core_discussion.services.post.UpdatePostService.Input(
                post.getCorePostId(),
                ServiceName.serviceName,
                input.title,
                input.content
        ));
    }
}

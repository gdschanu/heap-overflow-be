package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class UpdatePostService {
    private final hanu.gdsc.core_discussion.services.post.UpdatePostService updateCoreDiscussionPostService;
    private final PostRepository postRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id id;
        public String title;
        public String content;
    }

    public void execute(Input input) throws NotFoundException {
        Post post = postRepository.getById(input.id);
        if (post == null)
            throw new NotFoundException("Unknown post");
        updateCoreDiscussionPostService.execute(new hanu.gdsc.core_discussion.services.post.UpdatePostService.Input(
                post.getCorePostId(),
                ServiceName.serviceName,
                input.title,
                input.content
        ));
    }
}

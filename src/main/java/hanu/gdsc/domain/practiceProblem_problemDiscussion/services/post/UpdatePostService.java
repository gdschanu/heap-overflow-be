package hanu.gdsc.domain.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.domain.practiceProblem_problemDiscussion.repositories.PostRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.models.Post;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class UpdatePostService {
    private final hanu.gdsc.domain.core_discussion.services.post.UpdatePostService updateCoreDiscussionPostService;
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
        updateCoreDiscussionPostService.execute(new hanu.gdsc.domain.core_discussion.services.post.UpdatePostService.Input(
                post.getCorePostId(),
                ServiceName.serviceName,
                input.title,
                input.content
        ));
    }
}

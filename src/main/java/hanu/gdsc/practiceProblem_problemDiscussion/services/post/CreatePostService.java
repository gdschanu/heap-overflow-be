package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class CreatePostService {
    private final hanu.gdsc.core_discussion.services.post.CreatePostService createCoreDiscussionPostService;
    private final SearchProblemService searchProblemService;
    private final PostRepository postRepository;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        public Id problemId;
        public String title;
        public Id author;
        public String content;
    }

    public Id execute(Input input) {
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

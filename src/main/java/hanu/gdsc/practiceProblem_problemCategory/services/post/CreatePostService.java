package hanu.gdsc.practiceProblem_problemCategory.services.post;

import hanu.gdsc.practiceProblem_problem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem_problemCategory.domains.Post;
import hanu.gdsc.practiceProblem_problemCategory.repositories.post.PostRepository;
import hanu.gdsc.practiceProblem_problemCategory.services.core_post.CreateCorePostService;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class CreatePostService {
    private final CreateCorePostService createCorePostService;
    private final SearchProblemService searchProblemService;
    private final PostRepository postRepository;

    @Getter
    public static class Input extends CreateCorePostService.Input {
        private Id problemId;

        public Input(String title, Id author, String content, Id problemId) {
            super(title, author, content);
            this.problemId = problemId;
        }
    }

    public Id execute(Input input) {
        try {
            searchProblemService.getById(input.problemId);
        } catch (NotFoundError error) {
            throw new NotFoundError("Unknown problem, cannot create post for an unexist problem");
        }
        Id corePostId = createCorePostService.create(new CreateCorePostService.Input(
                input.getTitle(),
                input.getAuthor(),
                input.getContent()
        ));
        Post post = Post.create(input.problemId, corePostId);
        postRepository.save(post);
        try {
            searchProblemService.getById(input.problemId);
        } catch (NotFoundError error) {
            throw new NotFoundError("Unknown problem, cannot create post for an unexist problem");
        }
        return post.getId();
    }
}

package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class SearchPostService {
    private final hanu.gdsc.core_discussion.services.post.SearchPostService searchCoreDiscussionPostService;
    private final PostRepository postRepository;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        public Id id;
        public Id problemId;
        public String title;
        public Id author;
        public DateTime createdAt;
        public DateTime updatedAt;
        public String content;

    }

    public Output getById(Id id) {
        Post post = postRepository.getById(id);
        if (post == null)
            throw new NotFoundError("Unknown post");
        hanu.gdsc.core_discussion.domains.Post corePost = searchCoreDiscussionPostService.getById(
                post.getCorePostId(),
                ServiceName.serviceName
        );
        return new Output(
                id,
                post.getProblemId(),
                corePost.getTitle(),
                corePost.getAuthor(),
                corePost.getCreatedAt(),
                corePost.getUpdatedAt(),
                corePost.getContent()
        );
    }
}

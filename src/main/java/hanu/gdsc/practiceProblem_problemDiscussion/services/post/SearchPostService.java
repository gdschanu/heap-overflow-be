package hanu.gdsc.practiceProblem_problemDiscussion.services.post;

import hanu.gdsc.practiceProblem_problemDiscussion.config.ServiceName;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component(value = "PracticeProbem.SearchPostService")
@Slf4j
public class SearchPostService {
    private final hanu.gdsc.core_discussion.services.post.SearchPostService searchCoreDiscussionPostService;
    private final PostRepository postRepository;

    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "output", description = "Data Transfer Object for discussion post to render")
    public static class Output {
        @Schema(description = "specify id of the post discussion", example = "6304b3b1f7ca2823fcb1a02a")
        public Id id;
        @Schema(description = "specify id of the problem have this post discussion", example = "62aeff0d9081bab25998b0d1")
        public Id problemId;
        @Schema(description = "specify the title the post discussion", example = "how to solve this problem with javascript")
        public String title;
        @Schema(description = "specify id of the author who are created this post discussion", example = "62bdcf0d9081ccc25998b0d2")
        public Id author;
        @Schema(description = "specify the time of this post discussion created at", example = "2022-08-23T18:02:09.840771200+07:00[Asia/Saigon]1")
        public DateTime createdAt;
        @Schema(description = "specify the time of this post discussion updated at", example = "2022-08-23T18:02:09.840771200+07:00[Asia/Saigon]")
        public DateTime updatedAt;
        @Schema(description = "specify the content of this post discussion", example = "String blalalablablalbalbalbalbal")
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

    public List<Output> getPosts(Id problemId,
                                 int page,
                                 int perPage) {
        List<Post> posts = postRepository.getPosts(
                problemId,
                page,
                perPage
        );
        List<hanu.gdsc.core_discussion.domains.Post> corePosts = searchCoreDiscussionPostService.getByIds(
                posts.stream()
                        .map(post -> post.getCorePostId())
                        .collect(Collectors.toList()),
                ServiceName.serviceName
        );
        if (posts.size() != corePosts.size())
            log.error("Post size != Core Post size for practice problem " + problemId);
        List<Output> outputs = new ArrayList<>();
        for (Post post : posts) {
            for (hanu.gdsc.core_discussion.domains.Post corePost : corePosts) {
                if (post.getCorePostId().equals(corePost.getId())) {
                    outputs.add(new Output(
                            post.getId(),
                            post.getProblemId(),
                            corePost.getTitle(),
                            corePost.getAuthor(),
                            corePost.getCreatedAt(),
                            corePost.getUpdatedAt(),
                            corePost.getContent()
                    ));
                }
            }
        }
        return outputs;
    }
}

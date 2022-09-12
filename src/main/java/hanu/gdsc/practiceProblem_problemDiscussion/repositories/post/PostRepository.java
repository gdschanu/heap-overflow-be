package hanu.gdsc.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface PostRepository {
    public void save(Post post);

    public Post getById(Id id);

    public List<Post> getPosts(Id problemId,
                               int page,
                               int perPage);

    public long countPosts(Id problemId);
}

package hanu.gdsc.domain.practiceProblem_problemDiscussion.repositories;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.models.Post;

import java.util.List;

public interface PostRepository {
    public void save(Post post);

    public Post getById(Id id);

    public List<Post> getPosts(Id problemId,
                               int page,
                               int perPage);

    public long countPosts(Id problemId);

    public void deleteAllByProblemId(Id ProblemId);
}

package hanu.gdsc.core_discussion.repositories.post;

import hanu.gdsc.core_discussion.domains.Post;

import java.util.List;

public interface PostRepository {
    public void save(Post post);

    public List<Post> get(int page, int perPage);
}

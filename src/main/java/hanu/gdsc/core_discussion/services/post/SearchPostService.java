package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchPostService {
    public Post getById(Id id, String serviceToCreate);

    public List<Post> get(int page, int perPage);
}

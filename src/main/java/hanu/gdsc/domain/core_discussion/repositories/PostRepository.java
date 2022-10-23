package hanu.gdsc.domain.core_discussion.repositories;

import hanu.gdsc.domain.core_discussion.models.Post;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface PostRepository {

    Id save(Post post);

    Post getById(Id id, String serviceToCreate);

    public List<Post> getByIds(List<Id> ids, String serviceToCreate);

    public void deleteAllByIds(List<Id> corePostIds);
}

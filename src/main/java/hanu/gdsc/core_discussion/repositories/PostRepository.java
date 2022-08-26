package hanu.gdsc.core_discussion.repositories;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;

import java.util.Optional;

public interface PostRepository {

    Id save(Post post);
    Post getById(Id id, String serviceToCreate);
}

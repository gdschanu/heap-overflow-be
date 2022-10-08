package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;

import java.util.List;

public interface SearchPostService {
    public Post getById(Id id, String serviceToCreate) throws NotFoundException;

    public List<Post> getByIds(List<Id> ids, String serviceToCreate);
}

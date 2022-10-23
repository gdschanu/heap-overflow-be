package hanu.gdsc.domain.core_discussion.services.post;

import hanu.gdsc.domain.core_discussion.models.Post;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;

import java.util.List;

public interface SearchPostService {
    public Post getById(Id id, String serviceToCreate) throws NotFoundException;

    public List<Post> getByIds(List<Id> ids, String serviceToCreate);
}

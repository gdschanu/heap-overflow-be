package hanu.gdsc.practiceProblem_problemDiscussion.services.core_post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;

public interface SearchCorePostService {
    public Post getById(Id id);
}

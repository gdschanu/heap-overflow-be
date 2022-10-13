package hanu.gdsc.core_discussion.services.comment;

import hanu.gdsc.core_discussion.domains.Comment;
import hanu.gdsc.share.domains.Id;

import java.util.Set;

public interface SearchCommentService {
    Set<Comment> findAllByPostIdAndServiceToCreate(Id postId, String serviceToCreate);
}

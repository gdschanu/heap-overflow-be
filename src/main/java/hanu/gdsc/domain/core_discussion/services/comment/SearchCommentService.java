package hanu.gdsc.domain.core_discussion.services.comment;

import hanu.gdsc.domain.core_discussion.models.Comment;
import hanu.gdsc.domain.share.models.Id;

import java.util.Set;

public interface SearchCommentService {
    Set<Comment> findAllByPostIdAndServiceToCreate(Id postId, String serviceToCreate);
}

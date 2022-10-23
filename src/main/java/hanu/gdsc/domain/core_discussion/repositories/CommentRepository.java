package hanu.gdsc.domain.core_discussion.repositories;

import hanu.gdsc.domain.core_discussion.models.Comment;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;
import java.util.Set;

public interface CommentRepository {
    Set<Comment> findAllByPostIdAndServiceToCreate(Id postId, String serviceToCreate);
    void deleteAllByPostIds(List<Id> postIds);
}

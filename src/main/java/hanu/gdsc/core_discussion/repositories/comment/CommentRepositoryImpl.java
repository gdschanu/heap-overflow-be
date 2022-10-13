package hanu.gdsc.core_discussion.repositories.comment;

import hanu.gdsc.core_discussion.domains.Comment;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    @Autowired
    private CommentJPARepository commentJPARepository;

    @Override
    public Set<Comment> findAllByPostIdAndServiceToCreate(Id postId, String serviceToCreate) {
        Set<CommentEntity> commentEntities = commentJPARepository.findAllByPostIdAndServiceToCreate(postId.toString(), serviceToCreate);
        if(!commentEntities.isEmpty()) {
            return commentEntities.stream()
                    .map(CommentEntity::toDomain)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

    @Override
    public void deleteAllByPostIds(List<Id> postIds) {
        commentJPARepository.deleteAllByPostIds(postIds.stream().map(Id::toString).collect(Collectors.toList()));
    }
}

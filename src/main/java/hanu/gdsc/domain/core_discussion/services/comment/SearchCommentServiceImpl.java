package hanu.gdsc.domain.core_discussion.services.comment;

import hanu.gdsc.domain.core_discussion.models.Comment;
import hanu.gdsc.domain.core_discussion.repositories.CommentRepository;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class SearchCommentServiceImpl implements SearchCommentService{
    private CommentRepository commentRepository;

    @Override
    public Set<Comment> findAllByPostIdAndServiceToCreate(Id postId, String serviceToCreate) {
        return commentRepository.findAllByPostIdAndServiceToCreate(postId, serviceToCreate);
    }
}

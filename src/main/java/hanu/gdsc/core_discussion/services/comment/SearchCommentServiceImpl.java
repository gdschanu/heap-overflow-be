package hanu.gdsc.core_discussion.services.comment;

import hanu.gdsc.core_discussion.domains.Comment;
import hanu.gdsc.core_discussion.repositories.comment.CommentRepository;
import hanu.gdsc.share.domains.Id;
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

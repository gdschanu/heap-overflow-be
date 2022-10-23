package hanu.gdsc.domain.core_discussion.services.post;

import hanu.gdsc.domain.core_discussion.models.Post;
import hanu.gdsc.domain.core_discussion.repositories.PostRepository;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreatePostServiceImpl implements CreatePostService {
    private final PostRepository postRepository;

    @Override
    public Id execute(Input input) {
        Post post = Post.create(
                input.title,
                input.author,
                input.content,
                input.serviceToCreate
        );
        postRepository.save(post);
        return post.getId();
    }
}

package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.core_discussion.repositories.PostRepository;
import hanu.gdsc.core_discussion.domains.Post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreatePostServiceImpl implements CreatePostService {
    private final PostRepository postRepository;

    @Override
    public Id execute(Input input) {
        Post post = Post.create(
                input.getTitle(),
                input.getAuthor(),
                input.getContent(),
                input.getServiceToCreate()
        );
        postRepository.save(post);
        return post.getId();
    }
}

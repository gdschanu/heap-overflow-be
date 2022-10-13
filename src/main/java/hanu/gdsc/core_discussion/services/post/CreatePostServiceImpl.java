package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;

import hanu.gdsc.core_discussion.repositories.post.PostRepository;

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

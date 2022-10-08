package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.core_discussion.repositories.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SearchPostServiceImpl implements SearchPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getById(Id id, String serviceToCreate) throws NotFoundException {
        Post post = postRepository.getById(id, serviceToCreate);
        if (Objects.nonNull(post)) {
            return post;
        } else {
            throw new NotFoundException("Not Found This Post");
        }
    }

    @Override
    public List<Post> getByIds(List<Id> ids, String serviceToCreate) {
        return postRepository.getByIds(ids, serviceToCreate);
    }
}

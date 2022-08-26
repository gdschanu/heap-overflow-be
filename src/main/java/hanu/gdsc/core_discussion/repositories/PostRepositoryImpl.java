package hanu.gdsc.core_discussion.repositories;

import hanu.gdsc.core_discussion.domains.Post;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class PostRepositoryImpl implements PostRepository{
    @Autowired
    private PostJPARepository postJPARepository;

    @Override
    public Id save(Post post) {
        postJPARepository.save(PostEntity.toEntity(post));
        return post.getId();
    }

    @Override
    public Post getById(Id id, String serviceToCreate) {
        try {
            return PostEntity.toDomain(postJPARepository.getByIdAndServiceToCreate(id.toString(), serviceToCreate));
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}

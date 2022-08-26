package hanu.gdsc.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "PracticeProblem.PostRepositoryImpl")
public class PostRepositoryImpl implements PostRepository{
    @Autowired
    private PPPostJPARepository PPPostJpaRepository;

    @Override
    public void save(Post post) {
        PPPostJpaRepository.save(PPPostEntity.toEntity(post));
    }

    @Override
    public Post getById(Id id) {
        return PPPostEntity.toDomain(PPPostJpaRepository.getById(id.toString()));
    }
}

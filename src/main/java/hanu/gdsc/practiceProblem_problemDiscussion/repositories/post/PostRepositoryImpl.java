package hanu.gdsc.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.core_discussion.repositories.post.PostEntity;
import hanu.gdsc.practiceProblem_problemDiscussion.domains.Post;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "PracticeProblem.PostRepositoryImpl")
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private PPPostJPARepository pPPostJpaRepository;

    @Override
    public void save(Post post) {
        pPPostJpaRepository.save(PPPostEntity.toEntity(post));
    }

    @Override
    public Post getById(Id id) {
        return PPPostEntity.toDomain(pPPostJpaRepository.getById(id.toString()));
    }

    @Override
    public List<Post> getPosts(Id problemId,
                               int page,
                               int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<PPPostEntity> entities = null;
        if (problemId == null) {
            entities = pPPostJpaRepository
                    .findAll(pageable);
        } else  {
            entities = pPPostJpaRepository
                    .findByProblemId(
                            problemId.toString(),
                            pageable);
        }
        return entities.getContent()
                .stream()
                .map(e -> PPPostEntity.toDomain(e))
                .collect(Collectors.toList());
    }
}

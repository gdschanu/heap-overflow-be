package hanu.gdsc.infrastructure.practiceProblem_problemDiscussion.repositories.post;

import hanu.gdsc.domain.practiceProblem_problemDiscussion.models.Post;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.repositories.PostRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "PracticeProblem.PostRepositoryImpl")
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private PPPostJPARepository pPPostJpaRepository;
    @Autowired
    private hanu.gdsc.domain.core_discussion.repositories.PostRepository corePostRepository;

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
        Pageable pageable = PageRequest.of(page, perPage, Sort.by("createdAtMillis").descending());
        Page<PPPostEntity> entities = pPPostJpaRepository
                .findByProblemId(
                        problemId.toString(),
                        pageable);
        return entities.getContent()
                .stream()
                .map(e -> PPPostEntity.toDomain(e))
                .collect(Collectors.toList());
    }

    @Override
    public long countPosts(Id problemId) {
        return pPPostJpaRepository
                .countByProblemId(problemId.toString());
    }

    @Override
    public void deleteAllByProblemId(Id problemId) {
        List<PPPostEntity> pPPostEntitys = pPPostJpaRepository.findByProblemId(problemId.toString());
        List<Id> corePostIds = pPPostEntitys.stream()
                        .map(postEntity -> {
                            try {
                                return new Id(postEntity.getCorePostId());
                            } catch (InvalidInputException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList());
        corePostRepository.deleteAllByIds(corePostIds);
        pPPostJpaRepository.deleteAllByProblemId(problemId.toString());
    }
}

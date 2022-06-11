package hanu.gdsc.practiceProblem.repositories.likeCount;

import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LikeCountRepositoryImpl implements LikeCountRepository {
    @Autowired
    private LikeCountJPARepository jpaRepository;

    @Override
    public List<LikeCount> getByProblemIds(List<Id> ids) {
        List<LikeCountEntity> entities = jpaRepository
                .findByProblemIdIn(ids.stream().map(x -> x.toString())
                        .collect(Collectors.toList()));
        return entities.stream().map(x -> x.toDomain()).collect(Collectors.toList());
    }

    @Override
    public LikeCount getByProblemId(Id id) {
        try {
            return jpaRepository.findByProblemId(id.toString()).toDomain();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public void create(LikeCount cnt) {
        jpaRepository.save(LikeCountEntity.fromDomain(cnt));
    }
}

package hanu.gdsc.practiceProblem_problem.repositories.dislikeCount;

import hanu.gdsc.practiceProblem_problem.domains.DislikeCount;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DisLikeCountRepositoryImpl implements DislikeCountRepository {
    @Autowired
    private DislikeCountJPARepository dislikeCountJPARepository;

    @Override
    public List<DislikeCount> getByProblemIds(List<Id> ids) {
        List<DislikeCountEntity> entities = dislikeCountJPARepository
                .findByProblemIdIn(ids.stream().map(x -> x.toString())
                        .collect(Collectors.toList()));
        return entities.stream().map(x -> x.toDomain()).collect(Collectors.toList());
    }

    @Override
    public DislikeCount getByProblemId(Id id) {
        try {
            return dislikeCountJPARepository.findByProblemId(id.toString()).toDomain();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public void create(DislikeCount dislikeCount) {
        dislikeCountJPARepository.save(DislikeCountEntity.fromDomain(dislikeCount));
    }
}

package hanu.gdsc.practiceProblem.repositories;

import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DisLikeCountRepositoryImpl implements DislikeCountRepository {
    @Override
    public List<DislikeCount> getByProblemIds(List<Id> ids) {
        return null;
    }

    @Override
    public DislikeCount getByProblemId(Id id) {
        return null;
    }

    @Override
    public void create(DislikeCount dislikeCount) {

    }
}

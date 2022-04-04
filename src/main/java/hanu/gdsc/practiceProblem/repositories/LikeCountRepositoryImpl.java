package hanu.gdsc.practiceProblem.repositories;

import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeCountRepositoryImpl implements LikeCountRepository {
    @Override
    public List<LikeCount> getByProblemIds(List<Id> ids) {
        return null;
    }

    @Override
    public LikeCount getByProblemId(Id id) {
        return null;
    }

    @Override
    public void create(LikeCount likeCount) {

    }
}

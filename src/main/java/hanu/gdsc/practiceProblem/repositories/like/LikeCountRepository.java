package hanu.gdsc.practiceProblem.repositories.like;

import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface LikeCountRepository {
    public List<LikeCount> getByProblemIds(List<Id> ids);

    public LikeCount getByProblemId(Id id);

    public void create(LikeCount likeCount);
}

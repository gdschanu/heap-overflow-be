package hanu.gdsc.practiceProblem_problem.repositories.likeCount;

import hanu.gdsc.practiceProblem_problem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface LikeCountRepository {
    public List<LikeCount> getByProblemIds(List<Id> ids);

    public LikeCount getByProblemId(Id id);

    public void create(LikeCount likeCount);
}

package hanu.gdsc.practiceProblem_problem.services.likeCount;

import hanu.gdsc.practiceProblem_problem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchLikeCountService {
    public LikeCount getByProblemId(Id problemId);

    public List<LikeCount> getByProblemIds(List<Id> problemIds);

}

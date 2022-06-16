package hanu.gdsc.practiceProblemSubdomain.problemContext.services.likeCount;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.LikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchLikeCountService {
    public LikeCount getByProblemId(Id problemId);

    public List<LikeCount> getByProblemIds(List<Id> problemIds);

}

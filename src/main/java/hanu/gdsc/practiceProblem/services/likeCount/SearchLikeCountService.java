package hanu.gdsc.practiceProblem.services.likeCount;

import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchLikeCountService {
    public LikeCount getByProblemId(Id problemId);
}

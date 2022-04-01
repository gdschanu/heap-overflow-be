package hanu.gdsc.practiceProblem.services.dislikeCount;

import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchDislikeCountService {
    public DislikeCount getByProblemId(Id problemId);
}

package hanu.gdsc.practiceProblem.repositories.dislikeCount;

import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface DislikeCountRepository {
    public List<DislikeCount> getByProblemIds(List<Id> ids);

    public DislikeCount getByProblemId(Id id);

    public void create(DislikeCount dislikeCount);
}

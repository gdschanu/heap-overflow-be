package hanu.gdsc.practiceProblem_problem.services.likeCount;

import hanu.gdsc.practiceProblem_problem.domains.LikeCount;
import hanu.gdsc.practiceProblem_problem.repositories.likeCount.LikeCountRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchLikeCountServiceImpl implements SearchLikeCountService {
    private final LikeCountRepository likeCountRepository;

    @Override
    public LikeCount getByProblemId(Id problemId) {
        LikeCount cnt = likeCountRepository.getByProblemId(problemId);
        if (cnt == null) {
            throw new BusinessLogicError("Like Count not found.", "NOT_FOUND");
        }
        return cnt;
    }

    @Override
    public List<LikeCount> getByProblemIds(List<Id> problemIds) {
        return likeCountRepository.getByProblemIds(problemIds);
    }
}

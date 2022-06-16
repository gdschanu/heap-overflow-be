package hanu.gdsc.practiceProblemSubdomain.problemContext.services.dislikeCount;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.DislikeCount;
import hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.dislikeCount.DislikeCountRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SearchDislikeCountServiceImpl implements SearchDislikeCountService {
    private final DislikeCountRepository dislikeCountRepository;

    @Override
    public DislikeCount getByProblemId(Id problemId) {
        DislikeCount dislikeCount = dislikeCountRepository.getByProblemId(problemId);
        if (dislikeCount == null) {
            throw new BusinessLogicError("Dislike Count not found.", "NOT_FOUND");
        }
        return dislikeCount;
    }

    @Override
    public List<DislikeCount> getByProblemIds(List<Id> problemIds) {
        return dislikeCountRepository.getByProblemIds(problemIds);
    }
}
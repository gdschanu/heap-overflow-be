package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchContestServiceImpl implements SearchContestService {
    private final ContestRepository contestRepository;

    @Override
    public Contest getById(Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest không tồn tại.");
        }
        return contest;
    }

    @Override
    public List<Contest> search(int page, int perPage) {
        return contestRepository.search((page - 1) * perPage, page);
    }
}

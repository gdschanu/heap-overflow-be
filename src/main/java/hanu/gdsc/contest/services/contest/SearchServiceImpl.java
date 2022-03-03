package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final ContestRepository contestRepository;

    @Override
    public Contest getById(Id contestId) {
        return contestRepository.getById(contestId);
    }

    @Override
    public List<Contest> search(int page, int perPage) {
        return contestRepository.search((page - 1) * perPage, page);
    }
}

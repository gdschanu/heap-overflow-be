package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountContestServiceImpl implements CountContestService {

    @Autowired
    public ContestRepository contestRepository;

    @Override
    public long countContest() {
        return contestRepository.count();
    }
}

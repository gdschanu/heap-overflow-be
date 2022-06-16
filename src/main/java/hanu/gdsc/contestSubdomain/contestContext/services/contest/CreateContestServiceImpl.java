package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.contestSubdomain.contestContext.domains.Contest;
import hanu.gdsc.contestSubdomain.contestContext.repositories.contest.ContestRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateContestServiceImpl implements CreateContestService {
    private final ContestRepository contestRepository;

    @Override
    public Id create(Input input) {
        Contest contest = Contest.create(
                input.name,
                input.description,
                input.startAt,
                input.endAt,
                input.createdBy
        );
        contestRepository.create(contest);
        return contest.getId();
    }
}

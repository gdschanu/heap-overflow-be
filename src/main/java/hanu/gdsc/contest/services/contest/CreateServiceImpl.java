package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.repositories.ContestRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateServiceImpl implements CreateService {
    private final ContestRepository contestRepository;

    @Override
    public void create(Input input) {
        Contest contest = Contest.create(
                input.name,
                input.description,
                input.startAt,
                input.endAt,
                input.author
        );
        contestRepository.save(contest);
    }
}

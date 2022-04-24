package hanu.gdsc.contest.services.problem;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RemoveProblemServiceImpl implements RemoveProblemService {
    private final ContestRepository contestRepository;

    @Override
    public void execute(Id contestId, int ordinal) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest doesn't exist.", "UNEXIST_CONTEST");
        }
        contest.removeProblem(ordinal);
        contestRepository.update(contest);
    }
}

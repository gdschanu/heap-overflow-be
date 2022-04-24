package hanu.gdsc.contest.services.problem;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Problem;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service(value = "Contest.DeleteProblemServiceImpl")
public class DeleteProblemServiceImpl implements DeleteProblemService {
    private final ContestRepository contestRepository;
    private final hanu.gdsc.coreProblem.services.problem.DeleteProblemService deleteCoreProblemService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Id contestId, int ordinal) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest doesn't exist.", "UNEXIST_CONTEST");
        }

        Problem problem = contest.getProblem(ordinal);
        if (problem == null) {
            throw new BusinessLogicError("Unknown ordinal.", "UNKNOWN_ORDINAL");
        }

        contest.removeProblem(ordinal);

        deleteCoreProblemService.execute(problem.getCoreProblemId());
        contestRepository.update(contest);
    }
}

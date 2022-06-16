package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.contestSubdomain.contestContext.domains.Contest;
import hanu.gdsc.contestSubdomain.contestContext.domains.Problem;
import hanu.gdsc.contestSubdomain.contestContext.errors.InvalidOrdinalError;
import hanu.gdsc.contestSubdomain.contestContext.repositories.contest.ContestRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service(value = "Contest.DeleteProblemServiceImpl")
public class DeleteProblemServiceImpl implements DeleteProblemService {
    private final ContestRepository contestRepository;
    private final hanu.gdsc.coreSubdomain.problemContext.services.problem.DeleteProblemService deleteCoreProblemService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Id contestId, int ordinal) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundError("Unknown contest");
        }

        Problem problem = contest.getProblem(ordinal);
        if (problem == null) {
            throw new InvalidOrdinalError();
        }

        contest.removeProblem(ordinal);

        deleteCoreProblemService.execute(problem.getCoreProblemId());
        contestRepository.update(contest);
    }
}

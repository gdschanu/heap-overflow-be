package hanu.gdsc.contest.services.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Problem;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "Contest.UpdateProblemService")
@AllArgsConstructor
public class UpdateProblemServiceImpl implements UpdateProblemService {
    private final SearchProblemService searchProblemService;
    private final ContestRepository contestRepository;

    @Override
    public void addProblem(AddProblemInput input) {
        Contest contest = contestRepository.getById(input.contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest doesn't exist.", "UNEXIST_CONTEST");
        }
        SearchProblemService.Output coreProb = searchProblemService.getById(input.coreProblemId, ServiceName.serviceName);
        if (coreProb == null) {
            throw new BusinessLogicError("Problem doesn't exist.", "UNEXIST_CORE_PROBLEM");
        }
        Problem problem = Problem.create(input.ordinal, input.coreProblemId, input.score);
        contest.addProblem(problem);
        contestRepository.update(contest);
    }

    @Override
    public void removeProblem(Id contestId, Id coreProblemId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest doesn't exist.", "UNEXIST_CONTEST");
        }
        contest.removeProblem(coreProblemId);
        contestRepository.update(contest);
    }
}

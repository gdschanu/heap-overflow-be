package hanu.gdsc.contest.services.problem;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.contest.domains.Problem;
import hanu.gdsc.contest.repositories.ContestRepository;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Contest.CreateProblemService")
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private final SearchProblemService searchProblemService;
    private final ContestRepository contestRepository;

    private final hanu.gdsc.coreProblem.services.problem.CreateProblemService createCoreProblemService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Input input) {
        Contest contest = contestRepository.getById(input.contestId);
        if (contest == null) {
            throw new BusinessLogicError("Contest doesn't exist.", "UNEXIST_CONTEST");
        }
        Id coreProblemId = createCoreProblemService
                .execute(hanu.gdsc.coreProblem.services.problem.CreateProblemService.Input
                        .builder()
                        .name(input.createCoreProblemInput.name)
                        .description(input.createCoreProblemInput.description)
                        .author(input.createCoreProblemInput.author)
                        .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                        .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                        .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                        .serviceToCreate(ServiceName.serviceName)
                        .build());
        Problem problem = Problem.create(input.ordinal, coreProblemId, input.score);
        contest.addProblem(problem);
        contestRepository.update(contest);
    }

}

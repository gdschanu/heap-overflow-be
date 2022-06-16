package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.contestSubdomain.contestContext.domains.Contest;
import hanu.gdsc.contestSubdomain.contestContext.domains.Problem;
import hanu.gdsc.contestSubdomain.contestContext.repositories.contest.ContestRepository;
import hanu.gdsc.coreSubdomain.problemContext.services.problem.CreateProblemService;
import hanu.gdsc.coreSubdomain.problemContext.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Contest.CreateProblemService")
@AllArgsConstructor
public class AddProblemServiceImpl implements AddProblemService {
    private final SearchProblemService searchProblemService;
    private final ContestRepository contestRepository;

    private final CreateProblemService createCoreProblemService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Input input) {
        Contest contest = contestRepository.getById(input.contestId);
        if (contest == null) {
            throw new NotFoundError("Unknown contest");
        }
        Id coreProblemId = createCoreProblemService
                .execute(CreateProblemService.Input
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

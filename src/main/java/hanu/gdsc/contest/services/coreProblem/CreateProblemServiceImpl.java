package hanu.gdsc.contest.services.coreProblem;

import hanu.gdsc.contest.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private final hanu.gdsc.coreProblem.services.problem.CreateProblemService createCoreProblemService;

    @Override
    public Id execute(Input input) {
        Id coreProblemId = createCoreProblemService
                .execute(hanu.gdsc.coreProblem.services.problem.CreateProblemService.Input
                        .builder()
                        .name(input.name)
                        .description(input.description)
                        .author(input.author)
                        .createMemoryLimitInputs(input.createMemoryLimitInputs)
                        .createTimeLimitInputs(input.createTimeLimitInputs)
                        .allowedProgrammingLanguages(input.allowedProgrammingLanguages)
                        .serviceToCreate(ServiceName.serviceName)
                        .build());
        return coreProblemId;
    }
}

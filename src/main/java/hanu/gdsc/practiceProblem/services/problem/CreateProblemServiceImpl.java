package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.practiceProblem.domains.LikeCount;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.DislikeCountRepository;
import hanu.gdsc.practiceProblem.repositories.LikeCountRepository;
import hanu.gdsc.practiceProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component(value = "PracticeProblem.CreateProblemServiceImpl")
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private final hanu.gdsc.coreProblem.services.problem.CreateProblemService createCoreProblemService;
    private final ProblemRepository problemRepository;
    private final LikeCountRepository likeCountRepository;
    private final DislikeCountRepository dislikeCountRepository;

    @Override
    public Id create(Input input) {
        Id coreProblemId = createCoreProblemService.execute(hanu.gdsc.coreProblem.services.problem.CreateProblemService.Input.builder()
                .name(input.createCoreProblemInput.name)
                .description(input.createCoreProblemInput.description)
                .author(input.createCoreProblemInput.author)
                .createTestCaseInputs(input.createCoreProblemInput.createTestCaseInputs)
                .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                .serviceToCreate(ServiceName.serviceName)
                .build());

        Problem practiceProblem = Problem.create(coreProblemId, input.categoryIds, input.difficulty);
        problemRepository.create(practiceProblem);

        LikeCount likeCount = LikeCount.create(practiceProblem.getId());
        likeCountRepository.create(likeCount);

        DislikeCount dislikeCount = DislikeCount.create(practiceProblem.getId());
        dislikeCountRepository.create(dislikeCount);

        return practiceProblem.getId();
    }
}
    
package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.DislikeCount;
import hanu.gdsc.practiceProblem_problem.domains.LikeCount;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.dislikeCount.DislikeCountRepository;
import hanu.gdsc.practiceProblem_problem.repositories.likeCount.LikeCountRepository;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "PracticeProblem.CreateProblemServiceImpl")
@AllArgsConstructor
public class CreateProblemService {
    private final hanu.gdsc.core_problem.services.problem.CreateProblemService createCoreProblemService;
    private final ProblemRepository problemRepository;
    private final LikeCountRepository likeCountRepository;
    private final DislikeCountRepository dislikeCountRepository;

    @Builder
    public static class Input {
        public CreateCoreProblemInput createCoreProblemInput;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    @Builder
    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    public Id create(Input input) {
        Id coreProblemId = createCoreProblemService.execute(hanu.gdsc.core_problem.services.problem.CreateProblemService.Input.builder()
                .name(input.createCoreProblemInput.name)
                .description(input.createCoreProblemInput.description)
                .author(input.createCoreProblemInput.author)
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
    
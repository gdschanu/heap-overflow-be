package hanu.gdsc.domain.contest.services.contest;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.models.ParticipantCount;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantCountRepository;
import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.core_problem.services.problem.CreateProblemService;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateContestService {
    private final ContestRepository contestRepository;
    private final CreateProblemService createProblemService;

    private final ParticipantCountRepository participantCountRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "create problem input", description = "Data transfer object for problem to create in contest")
    public static class CreateProblemInput {
        @Schema(description = "specify the ordinal of problem", example = "1", required = true)
        public int ordinal;
        @Schema(description = "specify the score of problem", example = "10", required = true)
        public int score;
        @Schema(description = "specify the name of problem", example = "Sum array", required = true)
        public String name;
        @Schema(description = "specify the description of problem", example = "blablablablabla", required = true)
        public String description;
        @Schema(description = "specify the input to create memory limit of problem", required = true)
        public List<MemoryLimit.CreateInputML> memoryLimits;
        @Schema(description = "specify the input to create time limit of problem", required = true)
        public List<TimeLimit.CreateInputTL> timeLimits;
        @Schema(description = "specify the programming language of problem", required = true)
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
        public Id createdBy;
        public List<CreateProblemInput> problems;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Id create(Input input) throws InvalidInputException {
        List<Id> coreProblemIds = createProblemService
                .createMany(input.problems
                        .stream()
                        .map(createProblemInput -> new CreateProblemService.Input(
                                createProblemInput.name,
                                createProblemInput.description,
                                input.createdBy,
                                createProblemInput.memoryLimits,
                                createProblemInput.timeLimits,
                                createProblemInput.allowedProgrammingLanguages,
                                ContestServiceName.serviceName
                        ))
                        .collect(Collectors.toList()));
        List<ContestProblem> contestProblems = new ArrayList<>();
        int i = 0;
        for (CreateProblemInput createProblemInput : input.problems) {
            contestProblems.add(ContestProblem.create(
                    createProblemInput.ordinal,
                    coreProblemIds.get(i++),
                    createProblemInput.score
            ));
        }
        Contest contest = Contest.create(
                input.name,
                input.description,
                input.startAt,
                input.endAt,
                input.createdBy,
                contestProblems
        );
        contestRepository.create(contest);
        participantCountRepository.save(ParticipantCount.create(contest.getId()));
        return contest.getId();
    }
}

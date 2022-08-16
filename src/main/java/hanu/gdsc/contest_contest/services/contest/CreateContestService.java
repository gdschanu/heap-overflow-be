package hanu.gdsc.contest_contest.services.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hanu.gdsc.contest_contest.config.ServiceName;
import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.ContestProblem;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.core_problem.services.problem.CreateProblemService;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @Getter
    @Schema(title = "create problem input", description = "Data transfer object for problem to create in contest")
    public static class CreateProblemInput {
        @Schema(description = "specify the ordinal of problem", example = "1", required = true)
        private int ordinal;
        @Schema(description = "specify the score of problem", example = "10", required = true)
        private int score;
        @Schema(description = "specify the name of problem", example = "Sum array", required = true)
        private String name;
        @Schema(description = "specify the description of problem", example = "blablablablabla", required = true)
        private String description;
        @Schema(description = "specify the input to create memory limit of problem", required = true)
        private List<MemoryLimit.CreateInputML> memoryLimits;
        @Schema(description = "specify the input to create time limit of problem", required = true)
        private List<TimeLimit.CreateInputTL> timeLimits;
        @Schema(description = "specify the programming language of problem", required = true)
        private List<ProgrammingLanguage> allowedProgrammingLanguages;

        @JsonCreator
        public CreateProblemInput(@JsonProperty("ordinal") int ordinal,
                                  @JsonProperty("score") int score,
                                  @JsonProperty("name") String name,
                                  @JsonProperty("description") String description,
                                  @JsonProperty("memoryLimits") List<MemoryLimit.CreateInputML> memoryLimits,
                                  @JsonProperty("timeLimits") List<TimeLimit.CreateInputTL> timeLimits,
                                  @JsonProperty("allowedProgrammingLanguages") List<ProgrammingLanguage> allowedProgrammingLanguages) {
            this.ordinal = ordinal;
            this.score = score;
            this.name = name;
            this.description = description;
            this.memoryLimits = memoryLimits;
            this.timeLimits = timeLimits;
            this.allowedProgrammingLanguages = allowedProgrammingLanguages;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class Input {
        private String name;
        private String description;
        private DateTime startAt;
        private DateTime endAt;
        private Id createdBy;
        private List<CreateProblemInput> problems;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Id create(Input input) {
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
                                ServiceName.serviceName
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
        return contest.getId();
    }
}

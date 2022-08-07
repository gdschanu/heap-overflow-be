package hanu.gdsc.contest_contest.services.contest;

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

    @AllArgsConstructor
    @Getter
    public static class CreateProblemInput {
        private int ordinal;
        private int score;
        private String name;
        private String description;
        private List<MemoryLimit.CreateInput> memoryLimits;
        private List<TimeLimit.CreateInput> timeLimits;
        private List<ProgrammingLanguage> allowedProgrammingLanguages;
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

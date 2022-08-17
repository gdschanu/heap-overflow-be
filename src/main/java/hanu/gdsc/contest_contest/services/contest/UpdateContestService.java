package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.config.ServiceName;
import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.ContestProblem;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.core_problem.services.problem.CreateProblemService;
import hanu.gdsc.core_problem.services.problem.DeleteProblemService;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UpdateContestService {
    private final ContestRepository contestRepository;
    private final CreateProblemService createCoreProblemService;
    private final DeleteProblemService deleteCoreProblemService;


    @AllArgsConstructor
    @Getter
    public static class UpdateProblemInput {
        private int ordinal;
        private int score;
        private String name;
        private String description;
        private List<MemoryLimit.CreateInputML> memoryLimits;
        private List<TimeLimit.CreateInputTL> timeLimits;
        private List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id id;
        private String name;
        private String description;
        private DateTime startAt;
        private DateTime endAt;
        private List<UpdateProblemInput> problems;
        private Id updatedBy;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void execute(Input input) {
        Contest contest = contestRepository.getById(input.id);
        if (contest == null) {
            throw new BusinessLogicError("Contest không tồn tại.", "NOT_FOUND");
        }
        if (input.name != null) {
            contest.setName(input.name);
        }
        if (input.description != null) {
            contest.setDescription(input.name);
        }
        if (input.startAt != null && input.endAt != null) {
            contest.setStartAtAndEndAt(input.startAt, input.endAt);
        }
        if (input.problems != null) {
            deleteCoreProblemService.deleteMany(
                    contest.getProblems()
                            .stream()
                            .map(problem -> problem.getCoreProblemId())
                            .collect(Collectors.toList())
            );
            List<Id> coreProblemIds = createCoreProblemService
                    .createMany(input.problems
                            .stream()
                            .map(createProblemInput -> new CreateProblemService.Input(
                                    createProblemInput.name,
                                    createProblemInput.description,
                                    input.updatedBy,
                                    createProblemInput.memoryLimits,
                                    createProblemInput.timeLimits,
                                    createProblemInput.allowedProgrammingLanguages,
                                    ServiceName.serviceName
                            ))
                            .collect(Collectors.toList()));
            List<ContestProblem> contestProblems = new ArrayList<>();
            int i = 0;
            for (UpdateProblemInput updateProblemInput : input.problems) {
                contestProblems.add(ContestProblem.create(
                        updateProblemInput.ordinal,
                        coreProblemIds.get(i++),
                        updateProblemInput.score
                ));
            }
            contest.setProblems(contestProblems);
        }
        contestRepository.update(contest);
    }
}

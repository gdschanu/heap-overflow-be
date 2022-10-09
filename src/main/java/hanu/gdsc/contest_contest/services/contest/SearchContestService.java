package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.config.ServiceName;
import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.domains.ContestProblem;
import hanu.gdsc.contest_contest.domains.ParticipantCount;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.contest_contest.repositories.participantCount.ParticipantCountRepositoy;
import hanu.gdsc.core_problem.domains.KB;
import hanu.gdsc.core_problem.domains.Millisecond;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.services.problem.SearchProblemService;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchContestService {
    private final ContestRepository contestRepository;
    private final SearchProblemService searchCoreProblemService;

    private final ParticipantCountRepositoy participantCountRepositoy;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputMemoryLimit {
        public ProgrammingLanguage programmingLanguage;
        public KB memoryLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputTimeLimit {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputProblem {
        public int ordinal;
        public int score;
        public String name;
        public String description;
        public Id author;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputContest {
        public Id id;
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
        public Id createdBy;
        public List<OutputProblem> problems;
        public long version;
        public DateTime createdAt;
        public long numberOfParticipant;
    }

    private OutputContest toOutput(Contest contest, List<OutputProblem> problems) {
        ParticipantCount participantCount = participantCountRepositoy.getByContestId(contest.getId());
        return new OutputContest(
                contest.getId(),
                contest.getName(),
                contest.getDescription(),
                contest.getStartAt(),
                contest.getEndAt(),
                contest.getCreatedBy(),
                problems,
                contest.getVersion(),
                contest.getCreatedAt(),
                participantCount.getNum()
        );
    }

    private OutputProblem toOutputProblem(ContestProblem contestProblem, Problem coreProblem) {
        return new OutputProblem(
                contestProblem.getOrdinal(),
                contestProblem.getScore(),
                coreProblem.getName(),
                coreProblem.getDescription(),
                coreProblem.getAuthor(),
                coreProblem.getMemoryLimits()
                        .stream()
                        .map(coreProb -> new OutputMemoryLimit(
                                coreProb.getProgrammingLanguage(),
                                coreProb.getMemoryLimit()
                        ))
                        .collect(Collectors.toList()),
                coreProblem.getTimeLimits()
                        .stream()
                        .map(core -> new OutputTimeLimit(
                                core.getProgrammingLanguage(),
                                core.getTimeLimit()
                        ))
                        .collect(Collectors.toList()),
                coreProblem.getAllowedProgrammingLanguages()
        );
    }

    public OutputContest getById(Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundError("Contest doesn't exist");
        }
        List<Problem> coreProblems = searchCoreProblemService.getByIds(
                contest.getProblems()
                        .stream()
                        .map(contestProb -> contestProb.getCoreProblemId())
                        .collect(Collectors.toList()),
                ServiceName.serviceName
        );
        List<OutputProblem> problems = new ArrayList<>();
        for (ContestProblem contestProblem : contest.getProblems()) {
            for (Problem coreProblem : coreProblems) {
                if (contestProblem.getCoreProblemId().equals(coreProblem.getId())) {
                    problems.add(toOutputProblem(contestProblem, coreProblem));
                }
            }
        }
        return toOutput(contest, problems);
    }

    public List<OutputContest> get(int page, int perPage) {
        List<Contest> contests = contestRepository.get(page, perPage);
        List<Id> coreProblemIds = new ArrayList<>();
        for (Contest contest : contests)
            for (ContestProblem contestProblem : contest.getProblems())
                coreProblemIds.add(contestProblem.getCoreProblemId());
        List<Problem> coreProblems = searchCoreProblemService.getByIds(
                coreProblemIds,
                ServiceName.serviceName
        );
        List<OutputContest> result = new ArrayList<>();
        for (Contest contest : contests) {
            List<OutputProblem> problems = new ArrayList<>();
            for (ContestProblem contestProblem : contest.getProblems()) {
                for (Problem coreProblem : coreProblems) {
                    if (contestProblem.getCoreProblemId().equals(coreProblem.getId())) {
                        problems.add(toOutputProblem(contestProblem, coreProblem));
                    }
                }
            }
            result.add(toOutput(contest, problems));
        }
        return result;
    }

    public long countContest() {
        return contestRepository.count();
    }

    public long countContestParticipant(Id contestId) {
        return participantCountRepositoy.getByContestId(contestId).getNum();
    }
}

package hanu.gdsc.domain.contest.services.contest;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.models.ParticipantCount;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantCountRepository;
import hanu.gdsc.domain.core_problem.models.KB;
import hanu.gdsc.domain.core_problem.models.Millisecond;
import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.services.problem.SearchProblemService;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
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

    private final ParticipantCountRepository participantCountRepository;

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

    private OutputContest toOutput(Contest contest, List<OutputProblem> problems, long participantCount) {
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
                participantCount
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

    public OutputContest getById(Id contestId) throws NotFoundException {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundException("Contest doesn't exist");
        }
        List<Problem> coreProblems = searchCoreProblemService.getByIds(
                contest.getProblems()
                        .stream()
                        .map(contestProb -> contestProb.getCoreProblemId())
                        .collect(Collectors.toList()),
                ContestServiceName.serviceName
        );
        List<OutputProblem> problems = new ArrayList<>();
        for (ContestProblem contestProblem : contest.getProblems()) {
            for (Problem coreProblem : coreProblems) {
                if (contestProblem.getCoreProblemId().equals(coreProblem.getId())) {
                    problems.add(toOutputProblem(contestProblem, coreProblem));
                }
            }
        }
        return toOutput(
                contest,
                problems,
                participantCountRepository.getByContestId(contestId).getNum()
        );
    }

    public List<OutputContest> get(int page, int perPage) {
        List<Contest> contests = contestRepository.get(page, perPage);
        List<Id> coreProblemIds = new ArrayList<>();
        for (Contest contest : contests)
            for (ContestProblem contestProblem : contest.getProblems())
                coreProblemIds.add(contestProblem.getCoreProblemId());
        List<Problem> coreProblems = searchCoreProblemService.getByIds(
                coreProblemIds,
                ContestServiceName.serviceName
        );
        List<ParticipantCount> participantCounts = participantCountRepository.getByContestIds(
                contests.stream()
                        .map(contest -> contest.getId())
                        .collect(Collectors.toList())
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
            for (ParticipantCount participantCount : participantCounts) {
                if (participantCount.getContestId().equals(contest.getId())) {
                    result.add(toOutput(contest, problems, participantCount.getNum()));
                }
            }
        }
        return result;
    }

    public long countContest() {
        return contestRepository.count();
    }
}

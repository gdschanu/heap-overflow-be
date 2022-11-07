package hanu.gdsc.domain.practiceProblem_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.*;
import hanu.gdsc.domain.core_problem.services.acceptedProblem.SearchAcceptedProblemService;
import hanu.gdsc.domain.core_problem.services.submission.SearchSubmissionService;
import hanu.gdsc.domain.core_problem.services.submissionsCount.SearchSubmissionCountService;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Difficulty;
import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.practiceProblem_problem.repositories.problem.ProblemCountProjection;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service(value = "PracticeProblem.SearchProblemServiceImpl")
@AllArgsConstructor
public class SearchProblemService {
    private hanu.gdsc.domain.core_problem.services.problem.SearchProblemService searchCoreProblemProblemService;
    private ProblemRepository problemRepository;
    private SearchSubmissionService searchSubmissionService;

    private SearchAcceptedProblemService searchAcceptedProblemService;
    private SearchSubmissionCountService searchSubmissionCountService;
    private final ProgressRepository progressRepository;


    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "output", description = "Data transfer object for PracticeProblem to render")
    public static class Output {
        @Schema(example = "123456789abcd12abc13")
        public Id id;
        @Schema(example = "HARD")
        public Difficulty difficulty;
        @Schema(example = "Sum Array")
        public String name;
        @Schema(example = "blablabla")
        public String description;
        @Schema(example = "112412421abcd12abc124214")
        public Id author;
        @Schema
        public List<OutputMemoryLimit> memoryLimits;
        @Schema
        public List<OutputTimeLimit> timeLimits;
        @Schema
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public int acceptance;
        public List<String> tags;
        public Status status;
    }

    public static enum Status {
        DONE, UNDONE
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema
    public static class OutputMemoryLimit {
        @Schema(example = "JAVA")
        public ProgrammingLanguage programmingLanguage;
        @Schema(example = "1000")
        public KB memoryLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema
    public static class OutputTimeLimit {
        @Schema(example = "JAVA")
        public ProgrammingLanguage programmingLanguage;
        @Schema(example = "1000")
        public Millisecond timeLimit;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class OutputProgressData {
        private Difficulty difficulty;
        private int done;
        private long problems;
        private double percentage;
    }

    public Output getById(Id practiceProblemId) throws NotFoundException {
        return getById(practiceProblemId, null);
    }

    public Output getById(Id practiceProblemId, Id coderId) throws NotFoundException {
        hanu.gdsc.domain.practiceProblem_problem.models.Problem practiceProblem = problemRepository.getById(practiceProblemId);
        if (practiceProblem == null) {
            throw new NotFoundException("Unknown problem");
        }
        hanu.gdsc.domain.core_problem.models.Problem coreProblem = searchCoreProblemProblemService.getById(
                practiceProblem.getCoreProblemProblemId(),
                PracticeProblemProblemServiceName.serviceName
        );
        SubmissionCount submissionCount = searchSubmissionCountService.getByProblemId(
                practiceProblem.getCoreProblemProblemId(),
                PracticeProblemProblemServiceName.serviceName
        );
        AcceptedProblem acceptedProblem = coderId == null ?
                null :
                searchAcceptedProblemService.getByProblemIdAndCoderId(
                        practiceProblem.getCoreProblemProblemId(),
                        coderId,
                        PracticeProblemProblemServiceName.serviceName
                );
        return toOutput(practiceProblem, coreProblem, submissionCount, acceptedProblem != null);
    }

    public List<Output> get(int page, int perPage, Id coderId) {
        List<hanu.gdsc.domain.practiceProblem_problem.models.Problem> practiceProblems = problemRepository.get(
                page,
                perPage
        );
        return toListOutPut(practiceProblems, coderId);
    }

    public List<Output> getRecommendProblem(int count, Id coderId) {
        List<hanu.gdsc.domain.practiceProblem_problem.models.Problem> recommendProblems = problemRepository.getRecommendProblem(count);
        return toListOutPut(recommendProblems, coderId);
    }

    private List<Output> toListOutPut(List<hanu.gdsc.domain.practiceProblem_problem.models.Problem> problems, Id coderId) {
        final List<hanu.gdsc.domain.core_problem.models.Problem> coreProblems = searchCoreProblemProblemService.getByIds(
                problems.stream()
                        .map(hanu.gdsc.domain.practiceProblem_problem.models.Problem::getCoreProblemProblemId)
                        .collect(Collectors.toList()),
                PracticeProblemProblemServiceName.serviceName
        );
        final Map<Id, hanu.gdsc.domain.core_problem.models.Problem> coreProblemsIdMap = new HashMap<>();
        for (hanu.gdsc.domain.core_problem.models.Problem coreProb : coreProblems)
            coreProblemsIdMap.put(coreProb.getId(), coreProb);
        final List<SubmissionCount> submissionCounts = searchSubmissionCountService.getByProblemIds(
                problems.stream()
                        .map(hanu.gdsc.domain.practiceProblem_problem.models.Problem::getCoreProblemProblemId)
                        .collect(Collectors.toList()),
                PracticeProblemProblemServiceName.serviceName
        );
        final Map<Id, SubmissionCount> submissionCountMap = new HashMap<>();
        for (SubmissionCount submissionCount : submissionCounts)
            submissionCountMap.put(submissionCount.getProblemId(), submissionCount);
        final List<AcceptedProblem> acceptedProblems = coderId == null ?
                new ArrayList<>() :
                searchAcceptedProblemService.getByProblemIdsAndCoderId(
                        coderId,
                        problems.stream()
                                .map(hanu.gdsc.domain.practiceProblem_problem.models.Problem::getCoreProblemProblemId)
                                .collect(Collectors.toList()),
                        PracticeProblemProblemServiceName.serviceName
                );
        final Set<Id> acceptedProblemIdsSet = new HashSet<>();
        for (AcceptedProblem acceptedProblem : acceptedProblems)
            acceptedProblemIdsSet.add(acceptedProblem.getProblemId());
        return problems.stream()
                .map(p -> toOutput(
                                p,
                                coreProblemsIdMap.get(p.getCoreProblemProblemId()),
                                submissionCountMap.get(p.getCoreProblemProblemId()),
                                acceptedProblemIdsSet.contains(p.getCoreProblemProblemId())
                        )
                )
                .collect(Collectors.toList());
    }

    private Output toOutput(hanu.gdsc.domain.practiceProblem_problem.models.Problem practiceProblem,
                            hanu.gdsc.domain.core_problem.models.Problem coreProblem,
                            SubmissionCount submissionCount,
                            boolean accepted) {
        return new Output(
                practiceProblem.getId(),
                practiceProblem.getDifficulty(),
                coreProblem.getName(),
                coreProblem.getDescription(),
                coreProblem.getAuthor(),
                coreProblem.getMemoryLimits()
                        .stream()
                        .map(lim -> new OutputMemoryLimit(
                                lim.getProgrammingLanguage(),
                                lim.getMemoryLimit()
                        ))
                        .collect(Collectors.toList()),
                coreProblem.getTimeLimits()
                        .stream()
                        .map(lim -> new OutputTimeLimit(
                                lim.getProgrammingLanguage(),
                                lim.getTimeLimit()
                        ))
                        .collect(Collectors.toList()),
                coreProblem.getAllowedProgrammingLanguages(),
                submissionCount.acceptance(),
                Arrays.asList("Greedy"),
                accepted ? Status.DONE : Status.UNDONE
        );
    }

    public List<OutputProgressData> getProgress(Id coderId) {
        Progress progress = progressRepository.getByCoderId(coderId);
        OutputProgressData easyOutput = OutputProgressData.builder()
                .difficulty(Difficulty.EASY)
                .done(progress != null ? progress.getSolvedEasyProblems() : 0)
                .problems(countProblem())
                .percentage(progress != null ? Math.ceil((double) progress.getSolvedEasyProblems() / countProblem() * 100) : 0)
                .build();
        OutputProgressData mediumOutput = OutputProgressData.builder()
                .difficulty(Difficulty.MEDIUM)
                .done(progress != null ? progress.getSolvedMediumProblems() : 0)
                .problems(countProblem())
                .percentage(progress != null ? Math.ceil((double) progress.getSolvedMediumProblems() / countProblem() * 100) : 0)
                .build();
        OutputProgressData hardOutput = OutputProgressData.builder()
                .difficulty(Difficulty.HARD)
                .done(progress != null ? progress.getSolvedHardProblems() : 0)
                .problems(countProblem())
                .percentage(progress != null ? Math.ceil((double) progress.getSolvedHardProblems() / countProblem() * 100) : 0)
                .build();
        return List.of(easyOutput, mediumOutput, hardOutput);
    }

    public long countProblem() {
        return problemRepository.countProblem();
    }
}

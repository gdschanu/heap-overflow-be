package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.domains.KB;
import hanu.gdsc.core_problem.domains.Millisecond;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "PracticeProblem.SearchProblemServiceImpl")
@AllArgsConstructor
public class SearchProblemService {
    private hanu.gdsc.core_problem.services.problem.SearchProblemService searchCoreProblemProblemService;
    private ProblemRepository problemRepository;


    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id id;
        public Difficulty difficulty;
        public String name;
        public String description;
        public Id author;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

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

    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = problemRepository.getById(practiceProblemId);
        if (practiceProblem == null) {
            throw new NotFoundError("Unknown problem");
        }
        hanu.gdsc.core_problem.domains.Problem coreProblem = searchCoreProblemProblemService.getById(
                practiceProblem.getCoreProblemProblemId(),
                ServiceName.serviceName
        );
        return toOutput(practiceProblem, coreProblem);
    }

    public List<Output> get(int page, int perPage) {
        List<Problem> practiceProblems = problemRepository.get(
                page,
                perPage
        );
        return toListOutPut(practiceProblems);
    }

    public List<Output> getRecommendProblem(int count) {
        List<Problem> recommendProblems = problemRepository.getRecommendProblem(count);
        return toListOutPut(recommendProblems);
    }

    private List<Output> toListOutPut(List<Problem> problems) {
        List<hanu.gdsc.core_problem.domains.Problem> coreProblems = searchCoreProblemProblemService.getByIds(
                problems.stream()
                        .map(Problem::getCoreProblemProblemId)
                        .collect(Collectors.toList()),
                ServiceName.serviceName
        );
        Map<Id, hanu.gdsc.core_problem.domains.Problem> coreProblemsIdMap = new HashMap<>();
        for (hanu.gdsc.core_problem.domains.Problem coreProb : coreProblems)
            coreProblemsIdMap.put(coreProb.getId(), coreProb);
        return problems.stream()
                .map(p -> toOutput(p, coreProblemsIdMap.get(p.getCoreProblemProblemId())))
                .collect(Collectors.toList());
    }

    private Output toOutput(Problem practiceProblem, hanu.gdsc.core_problem.domains.Problem coreProblem) {
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
                coreProblem.getAllowedProgrammingLanguages()
        );
    }

    public long countProblem() {
        return problemRepository.countProblem();
    }
}

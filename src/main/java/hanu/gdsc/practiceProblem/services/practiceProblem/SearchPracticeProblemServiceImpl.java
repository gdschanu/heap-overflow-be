package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchPracticeProblemServiceImpl implements SearchPracticeProblemService {
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;
    @Autowired
    private SearchProblemService searchProblemService;

    @Override
    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = practiceProblemRepository.getById(practiceProblemId);
        if (practiceProblem == null) {
            throw new BusinessLogicError("Không tìm thấy bài toán phù hợp.", "NOT_FOUND");
        }
        hanu.gdsc.coreProblem.domains.Problem coreProblem = searchProblemService.getById(practiceProblem.getCoreProblemId());
        if (coreProblem == null) {
            throw new BusinessLogicError("Không tìm thấy bài toán phù hợp.", "NOT_FOUND");
        }
        return toOutput(practiceProblem, coreProblem);
    }

    @Override
    public List<Output> get(int page, int perPage) {
        List<Problem> practiceProblems = practiceProblemRepository.get(
                page,
                perPage
        );
        List<hanu.gdsc.coreProblem.domains.Problem> coreProblems = searchProblemService
                .getByIds(practiceProblems.stream()
                        .map(x -> x.getCoreProblemId())
                        .collect(Collectors.toList()));
        List<Output> res = new ArrayList<>();
        for (Problem practiceProb : practiceProblems) {
            hanu.gdsc.coreProblem.domains.Problem coreProb = null;
            for (hanu.gdsc.coreProblem.domains.Problem cp : coreProblems) {
                if (cp.getId().equals(practiceProb.getCoreProblemId())) {
                    coreProb = cp;
                }
            }
            res.add(toOutput(practiceProb, coreProb));
        }
        return res;
    }

    private Output toOutput(Problem practiceProblem, hanu.gdsc.coreProblem.domains.Problem coreProblem) {
        List<OutputTestCase> outputTestCases = new ArrayList<>();
        for (TestCase testCase : coreProblem.getTestCases()) {
            if (testCase.isSample() == true) {
                outputTestCases.add(
                        OutputTestCase.builder()
                                .input(testCase.getInput())
                                .expectedOutput(testCase.getExpectedOutput())
                                .ordinal(testCase.getOrdinal())
                                .isSample(testCase.isSample())
                                .description(testCase.getDescription())
                                .build()
                );
            }
        }
        List<OutputMemoryLimit> outputMemoryLimits = new ArrayList<>();
        for (MemoryLimit memoryLimit : coreProblem.getMemoryLimits()) {
            outputMemoryLimits.add(
                    OutputMemoryLimit.builder()
                            .programmingLanguage(memoryLimit.getProgrammingLanguage())
                            .memoryLimit(memoryLimit.getMemoryLimit())
                            .build()
            );
        }
        List<OutputTimeLimit> outputTimeLimits = new ArrayList<>();
        for (TimeLimit timeLimit : coreProblem.getTimeLimits()) {
            outputTimeLimits.add(
                    OutputTimeLimit.builder()
                            .programmingLanguage(timeLimit.getProgrammingLanguage())
                            .timeLimit(timeLimit.getTimeLimit())
                            .build()
            );
        }
        return Output.builder()
                .practiceProblem(
                        OutputPracticeProblem.builder()
                                .id(practiceProblem.getId())
                                .version(practiceProblem.getVersion())
                                .coreProblemId(practiceProblem.getCoreProblemId())
                                .likeCount(practiceProblem.getLikeCount())
                                .dislikeCount(practiceProblem.getDislikeCount())
                                .difficulty(practiceProblem.getDifficulty())
                                .build()
                )
                .coreProblem(
                        OutputCoreProblem.builder()
                                .id(coreProblem.getId())
                                .version(coreProblem.getVersion())
                                .name(coreProblem.getName())
                                .description(coreProblem.getDescription())
                                .author(coreProblem.getAuthor())
                                .testCases(outputTestCases)
                                .memoryLimits(outputMemoryLimits)
                                .timeLimits(outputTimeLimits)
                                .allowedProgrammingLanguages(coreProblem.getAllowedProgrammingLanguages())
                                .build()
                )
                .build();
    }
}

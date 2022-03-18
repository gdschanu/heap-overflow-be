package hanu.gdsc.practiceProblem.services.practiceProblem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService.OutputCoreProblem;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService.OutputMemoryLimit;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService.OutputTestCase;
import hanu.gdsc.coreProblem.services.problem.SearchProblemService.OutputTimeLimit;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class SearchPracticeProblemServiceImpl implements SearchPracticeProblemService{
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;
    @Autowired
    private SearchProblemService searchProblemService;

    @Override
    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = practiceProblemRepository.getById(practiceProblemId);
        hanu.gdsc.coreProblem.domains.Problem coreProblem = searchProblemService.getById(practiceProblem.getCoreProlemId());
        if (practiceProblem == null || coreProblem == null) {
            throw new BusinessLogicError("Không tìm thấy bài toán phù hợp", "NOT_FOUND");
        }
        List<OutputTestCase> outputTestCases = new ArrayList<>();
        for(TestCase testCase : coreProblem.getTestCases()) {
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
        List<OutputMemoryLimit> outputMemoryLimits = new ArrayList<>();
        for(MemoryLimit memoryLimit : coreProblem.getMemoryLimits()) {
            outputMemoryLimits.add(
                OutputMemoryLimit.builder()
                    .programmingLanguage(memoryLimit.getProgrammingLanguage())
                    .memoryLimit(memoryLimit.getMemoryLimit().getValue())
                    .build()
            );
        }
        List<OutputTimeLimit> outputTimeLimits = new ArrayList<>();
        for(TimeLimit timeLimit : coreProblem.getTimeLimits()) {
            outputTimeLimits.add(
                OutputTimeLimit.builder()
                    .programmingLanguage(timeLimit.getProgrammingLanguage())
                    .timeLimit(timeLimit.getTimeLimit().getValue())
                    .build()
            );
        }
        return Output.builder()
                .practiceProblem(
                    OutputPraticeProblem.builder()
                        .id(practiceProblem.getId().toString())
                        .version(practiceProblem.getVersion())
                        .coreProlemId(practiceProblem.getCoreProlemId().toString())
                        .likeCount(practiceProblem.getLikeCount())
                        .dislikeCount(practiceProblem.getDislikeCount())
                        .category(practiceProblem.getCategory().getName())
                        .build()
                )
                .coreProblem(
                    OutputCoreProblem.builder()
                        .id(coreProblem.getId().toString())
                        .version(coreProblem.getVersion())
                        .name(coreProblem.getName())
                        .description(coreProblem.getDescription())
                        .author(coreProblem.getAuthor().toString())
                        .testCases(outputTestCases)
                        .memoryLimits(outputMemoryLimits)
                        .timeLimits(outputTimeLimits)
                        .allowedProgrammingLanguages(coreProblem.getAllowedProgrammingLanguages())
                        .build()
                )
                .build();
    }
}

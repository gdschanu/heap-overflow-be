package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SearchProblemServiceImpl implements SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Output getById(Id id) {
        Problem problem = problemRepository.getById(id);
        if (problem == null) {
            throw new BusinessLogicError("Could not found problem", "NOT_FOUND");
        }
        return toOutput(problem);
    }

    private Output toOutput(Problem problem) {
        return Output.builder()
                .name(problem.getName())
                .description(problem.getDescription())
                .author(problem.getAuthor())
                .testCases(problem.getTestCases().stream().map(t -> OutputTestCase.builder()
                        .input(t.getInput())
                        .expectedOutput(t.getExpectedOutput())
                        .ordinal(t.getOrdinal())
                        .isSample(t.isSample())
                        .description(t.getDescription())
                        .build()).collect(Collectors.toList()))
                .memoryLimits(problem.getMemoryLimits().stream()
                        .map(m -> OutputMemoryLimit.builder()
                                .programmingLanguage(m.getProgrammingLanguage())
                                .memoryLimit(m.getMemoryLimit())
                                .build())
                        .collect(Collectors.toList()))
                .timeLimits(problem.getTimeLimits().stream()
                        .map(t -> OutputTimeLimit.builder()
                                .programmingLanguage(t.getProgrammingLanguage())
                                .timeLimit(t.getTimeLimit())
                                .build())
                        .collect(Collectors.toList()))
                .allowedProgrammingLanguages(problem.getAllowedProgrammingLanguages())
                .build();
    }
}

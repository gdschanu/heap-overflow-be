package hanu.gdsc.practiceProblem_problem.services.testCase;

import hanu.gdsc.core_problem.domains.TestCase;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchTestCaseService {
    private final hanu.gdsc.core_problem.services.testCase.SearchTestCaseService searchCoreTestCaseService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    @Getter
    public static class Output {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    public List<Output> getSampleTestcasesOfProblem(Id problemId) {
        Problem problem = problemRepository.getById(problemId);
        if (problem == null)
            throw new NotFoundError("Unknown problem, problem must be exist in order to have test case");
        return searchCoreTestCaseService.getSampleTestCases(
                        problem.getCoreProblemProblemId(),
                        ServiceName.serviceName
                )
                .stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private Output toOutput(TestCase testCase) {
        return new Output(
                testCase.getProblemId(),
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.getOrdinal(),
                testCase.isSample(),
                testCase.getDescription()
        );
    }
}

package hanu.gdsc.domain.practiceProblem_problem.services.testCase;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.practiceProblem_problem.config.PracticeProblemProblemServiceName;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchPracticeProblemTestCaseService {
    private final hanu.gdsc.domain.core_problem.services.testCase.SearchTestCaseService searchCoreTestCaseService;
    private final ProblemRepository problemRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public boolean isSample;
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public String description;
    }

    public List<Output> getSampleTestcasesOfProblem(Id problemId) throws NotFoundException {
        final Problem problem = problemRepository.getById(problemId);
        if (problem == null)
            throw new NotFoundException("Unknown problem, problem must be exist in order to have test case");
        return searchCoreTestCaseService.getSampleTestCases(
                        problem.getCoreProblemId(),
                        PracticeProblemProblemServiceName.serviceName
                )
                .stream()
                .map(testCase -> toOutput(testCase, problem.getId()))
                .collect(Collectors.toList());
    }

    private Output toOutput(TestCase testCase, Id practiceProblemId) {
        return new Output(
                testCase.isSample(),
                practiceProblemId,
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.getOrdinal(),
                testCase.getDescription()
        );
    }
}

package hanu.gdsc.domain.practiceProblem_problem.services.testCase;

import hanu.gdsc.domain.core_problem.models.TestCase;
import hanu.gdsc.domain.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.domain.practiceProblem_problem.domains.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchTestCaseService {
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
        Problem problem = problemRepository.getById(problemId);
        if (problem == null)
            throw new NotFoundException("Unknown problem, problem must be exist in order to have test case");
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
                testCase.isSample(),
                testCase.getProblemId(),
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.getOrdinal(),
                testCase.getDescription()
        );
    }
}

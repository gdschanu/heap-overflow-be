package hanu.gdsc.practiceProblem_problem.services.testCase;

import hanu.gdsc.core_problem.domains.TestCase;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchTestCaseService {
    private final hanu.gdsc.core_problem.services.testCase.SearchTestCaseService searchCoreTestCaseService;

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
        return searchCoreTestCaseService.getSampleTestCases(
                        problemId,
                        ServiceName.serviceName
                ).stream()
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

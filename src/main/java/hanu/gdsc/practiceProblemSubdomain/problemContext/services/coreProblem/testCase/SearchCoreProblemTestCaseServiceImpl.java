package hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.testCase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreSubdomain.problemContext.domains.TestCase;
import hanu.gdsc.coreSubdomain.problemContext.services.testCase.SearchTestCaseService;
import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;
import hanu.gdsc.share.domains.Id;

@Service
public class SearchCoreProblemTestCaseServiceImpl implements SearchCoreProblemTestCaseService {
    @Autowired
    private SearchTestCaseService searchTestCaseService;

    @Override
    public List<Output> get(Id problemId) {
        return searchTestCaseService.getByProblemId(problemId, ServiceName.serviceName).stream()
                .map(t -> toOutput(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<Output> getSampleTestCases(Id problemId) {
        return searchTestCaseService.getSampleTestCases(problemId, ServiceName.serviceName).stream()
                .map(t -> toOutput(t))
                .collect(Collectors.toList());
    }

    private Output toOutput(TestCase testCase) {
        return Output.builder()
                .problemId(testCase.getProblemId())
                .input(testCase.getInput())
                .expectedOutput(testCase.getExpectedOutput())
                .ordinal(testCase.getOrdinal())
                .isSample(testCase.isSample())
                .description(testCase.getDescription())
                .build();
    }
    
}

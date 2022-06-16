package hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.testCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreSubdomain.problemContext.services.testCase.CreateTestCaseService;
import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;

@Service
public class CreateCoreProblemTestCaseServiceImpl implements CreateCoreProblemTestCaseService {
    @Autowired
    private CreateTestCaseService createTestCaseService;

    @Override
    public void create(List<Input> inputs) {
        for(Input input : inputs) {
            createTestCaseService.create(CreateTestCaseService.Input.builder()
            .problemId(input.problemId)
            .input(input.input)
            .expectedOutput(input.expectedOutput)
            .ordinal(input.ordinal)
            .isSample(input.isSample)
            .description(input.description)
            .serviceToCreate(ServiceName.serviceName)
            .build()
            );
        }
    }
}

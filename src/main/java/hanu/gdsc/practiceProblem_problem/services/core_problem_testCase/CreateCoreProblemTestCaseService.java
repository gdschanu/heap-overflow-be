package hanu.gdsc.practiceProblem_problem.services.core_problem_testCase;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.core_problem.services.testCase.CreateTestCaseService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;

@Service
public class CreateCoreProblemTestCaseService {
    @Autowired
    private CreateTestCaseService createTestCaseService;

    @Builder
    public static class Input {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
        public String serviceToCreate;
    }

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

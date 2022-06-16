package hanu.gdsc.practiceProblemSubdomain.problemContext.services.core.problem.testCase;

import org.springframework.stereotype.Service;

import hanu.gdsc.coreSubdomain.problemContext.services.testCase.UpdateTestCaseService;
import hanu.gdsc.coreSubdomain.problemContext.services.testCase.UpdateTestCaseService.Input;
import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateCoreProblemTestCaseServiceImpl implements UpdateCoreProblemTestCaseService {
    
    private final UpdateTestCaseService updateTestCaseService;

    @Override
    public void update(Input input) {
        input.serviceToCreate = ServiceName.serviceName;
        updateTestCaseService.update(input);
    }
    
}

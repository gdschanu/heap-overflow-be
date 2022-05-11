package hanu.gdsc.practiceProblem.services.coreProblem;

import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.testCase.UpdateTestCaseService;
import hanu.gdsc.coreProblem.services.testCase.UpdateTestCaseService.Input;
import hanu.gdsc.practiceProblem.config.ServiceName;
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

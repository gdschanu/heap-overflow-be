package hanu.gdsc.practiceProblem_problem.services.core_problem.testCase;

import org.springframework.stereotype.Service;

import hanu.gdsc.core_problem.services.testCase.UpdateTestCaseService;
import hanu.gdsc.core_problem.services.testCase.UpdateTestCaseService.Input;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
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

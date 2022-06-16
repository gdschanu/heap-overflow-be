package hanu.gdsc.practiceProblem_problem.services.core_problem.testCase;

import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.core_problem.services.testCase.DeleteTestCaseService;
import hanu.gdsc.share.domains.Id;

@Service
public class DeleteCoreProblemTestCaseServiceImpl implements DeleteCoreProblemTestCaseService {
    @Autowired
    private DeleteTestCaseService deleteTestCaseService;

    @Override
    public void deleteById(Id id) {
        deleteTestCaseService.deleteById(id, ServiceName.serviceName);
    }
    
}

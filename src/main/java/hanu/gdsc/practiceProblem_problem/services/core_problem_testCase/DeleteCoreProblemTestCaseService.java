package hanu.gdsc.practiceProblem_problem.services.core_problem_testCase;

import hanu.gdsc.core_problem.services.testCase.DeleteTestCaseService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCoreProblemTestCaseService {
    @Autowired
    private DeleteTestCaseService deleteTestCaseService;

    public void deleteById(Id id) {
        deleteTestCaseService.deleteById(id, ServiceName.serviceName);
    }

}

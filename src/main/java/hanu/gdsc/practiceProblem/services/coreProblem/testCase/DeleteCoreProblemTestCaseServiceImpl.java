package hanu.gdsc.practiceProblem.services.coreProblem.testCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.testCase.DeleteTestCaseService;
import hanu.gdsc.practiceProblem.config.ServiceName;
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

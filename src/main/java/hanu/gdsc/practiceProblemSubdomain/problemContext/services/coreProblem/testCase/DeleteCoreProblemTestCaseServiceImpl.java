package hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.testCase;

import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreSubdomain.problemContext.services.testCase.DeleteTestCaseService;
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

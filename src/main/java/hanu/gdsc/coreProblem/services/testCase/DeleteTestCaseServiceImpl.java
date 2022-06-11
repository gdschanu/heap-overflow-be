package hanu.gdsc.coreProblem.services.testCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class DeleteTestCaseServiceImpl implements DeleteTestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        testCaseRepository.deleteById(id, serviceToCreate);
    }
    
}

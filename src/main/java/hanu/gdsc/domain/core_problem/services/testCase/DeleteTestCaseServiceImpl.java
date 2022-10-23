package hanu.gdsc.domain.core_problem.services.testCase;

import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.share.models.Id;

@Service
public class DeleteTestCaseServiceImpl implements DeleteTestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public void deleteById(Id id, String serviceToCreate) {
        testCaseRepository.deleteById(id, serviceToCreate);
    }
    
}

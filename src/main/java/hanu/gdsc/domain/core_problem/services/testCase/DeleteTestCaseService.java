package hanu.gdsc.domain.core_problem.services.testCase;

import hanu.gdsc.domain.share.models.Id;

public interface DeleteTestCaseService {
    public void deleteById(Id id, String serviceToCreate);
}

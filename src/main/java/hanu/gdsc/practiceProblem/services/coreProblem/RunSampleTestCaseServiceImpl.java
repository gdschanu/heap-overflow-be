package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.contest.config.ServiceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "PracticeProblem.CoreProblem.RunSampleTestCaseService")
public class RunSampleTestCaseServiceImpl implements RunSampleTestCaseService {
    @Autowired
    private hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService runSampleTestCaseService;

    @Override
    public hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService.Output runSampleTestCase(Input input) {
        return runSampleTestCaseService.runSampleTestCase(
                hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService.Input.builder()
                        .coderId(input.coderId)
                        .problemId(input.problemId)
                        .serviceToCreate(ServiceName.serviceName)
                        .code(input.code)
                        .programmingLanguage(input.programmingLanguage)
                        .build()
        );
    }
}

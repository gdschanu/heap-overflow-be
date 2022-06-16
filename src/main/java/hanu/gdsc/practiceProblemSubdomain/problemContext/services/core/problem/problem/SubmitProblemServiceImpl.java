package hanu.gdsc.practiceProblemSubdomain.problemContext.services.core.problem.problem;

import hanu.gdsc.coreSubdomain.problemContext.services.submit.SubmitService;
import hanu.gdsc.practiceProblemSubdomain.problemContext.config.ServiceName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "PracticeProblem.CoreProblem.SubmitProblemServiceImpl")
@AllArgsConstructor
public class SubmitProblemServiceImpl implements SubmitProblemService {
    private final SubmitService submitService;

    @Override
    public SubmitService.Output submit(Input input) {
        return submitService.submit(SubmitService.Input.builder()
                .coderId(input.coderId)
                .problemId(input.problemId)
                .serviceToCreate(ServiceName.serviceName)
                .code(input.code)
                .programmingLanguage(input.programmingLanguage)
                .build());
    }
}

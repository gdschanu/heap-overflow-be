package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.services.submit.SubmitService;
import hanu.gdsc.practiceProblem.config.ServiceName;
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

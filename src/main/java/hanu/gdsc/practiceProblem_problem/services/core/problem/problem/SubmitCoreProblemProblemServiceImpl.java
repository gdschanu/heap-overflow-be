package hanu.gdsc.practiceProblem_problem.services.core.problem.problem;

import hanu.gdsc.core_problem.services.submit.SubmitService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "PracticeProblem.CoreProblem.SubmitProblemServiceImpl")
@AllArgsConstructor
public class SubmitCoreProblemProblemServiceImpl implements SubmitCoreProblemProblemService {
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

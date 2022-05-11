package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.ProblemRepository;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component(value = "PracticeProblem.RunSampleTestCaseService")
@AllArgsConstructor
public class RunSampleTestCaseServiceImpl implements RunSampleTestCaseService {
    private final hanu.gdsc.practiceProblem.services.coreProblem.RunSampleTestCaseService
            coreProblemRunSampleTestCaseService;
    private final ProblemRepository problemRepository;

    @Override
    public hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService.Output runSampleTestCase(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new NotFoundError("Problem not found");
        }
        return coreProblemRunSampleTestCaseService.runSampleTestCase(
                hanu.gdsc.practiceProblem.services.coreProblem.RunSampleTestCaseService.Input.builder()
                        .coderId(input.coderId)
                        .problemId(problem.getCoreProblemProblemId())
                        .code(input.code)
                        .programmingLanguage(input.programmingLanguage)
                        .build()
        );
    }
    
}

package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.coreProblem.services.submit.SubmitService;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmitProblemServiceImpl implements SubmitProblemService {
    private ProblemRepository problemRepository;

    private hanu.gdsc.practiceProblem.services.coreProblem.problem.SubmitProblemService submitCoreProblemService;

    @Override
    public SubmitService.Output submit(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new NotFoundError("Problem not found");
        }
        return submitCoreProblemService.submit(hanu.gdsc.practiceProblem.services.coreProblem.problem.SubmitProblemService.Input.builder()
                .coderId(input.coderId)
                .problemId(problem.getCoreProblemProblemId())
                .code(input.code)
                .programmingLanguage(input.programmingLanguage)
                .build());
    }
}

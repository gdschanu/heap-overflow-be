package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.services.submit.SubmitService;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.practiceProblem_problem.services.core_problem_problem.SubmitCoreProblemProblemService;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmitProblemServiceImpl implements SubmitProblemService {
    private ProblemRepository problemRepository;

    private SubmitCoreProblemProblemService submitCoreProblemService;

    @Override
    public SubmitService.Output submit(Input input) {
        Problem problem = problemRepository.getById(input.problemId);
        if (problem == null) {
            throw new NotFoundError("Problem not found");
        }
        return submitCoreProblemService.submit(SubmitCoreProblemProblemService.Input.builder()
                .coderId(input.coderId)
                .problemId(problem.getCoreProblemProblemId())
                .code(input.code)
                .programmingLanguage(input.programmingLanguage)
                .build());
    }
}

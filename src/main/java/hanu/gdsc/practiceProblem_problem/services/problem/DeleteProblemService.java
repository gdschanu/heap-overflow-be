package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class DeleteProblemService {
    private ProblemRepository problemRepository;
    private hanu.gdsc.core_problem.services.problem.DeleteProblemService deleteCoreProblemService;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteById(Id id) {
        Problem problem = problemRepository.getById(id);
        if (problem == null)
            throw new NotFoundError("Unknown problem.");
        deleteCoreProblemService.deleteById(problem.getCoreProblemProblemId());
        problemRepository.deleteById(id);
    }
}

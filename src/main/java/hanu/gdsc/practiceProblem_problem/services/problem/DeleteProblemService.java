package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteProblemService {
    private ProblemRepository problemRepository;
    private hanu.gdsc.core_problem.services.problem.DeleteProblemService deleteCoreProblemService;
    private TestCaseRepository testCaseRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteById(Id id) throws NotFoundException {
        Problem problem = problemRepository.getById(id);
        if (problem == null)
            throw new NotFoundException("Unknown problem.");
        deleteCoreProblemService.deleteById(problem.getCoreProblemProblemId());
        problemRepository.deleteById(id);
        testCaseRepository.deleteByProblemId(id);
    }
}

package hanu.gdsc.domain.practiceProblem_problem.services.problem;

import hanu.gdsc.domain.core_problem.repositories.SubmissionRepository;
import hanu.gdsc.domain.core_problem.repositories.TestCaseRepository;
import hanu.gdsc.domain.practiceProblem_problem.models.Problem;
import hanu.gdsc.domain.practiceProblem_problemDiscussion.repositories.PostRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteProblemService {
    private ProblemRepository problemRepository;
    private hanu.gdsc.domain.core_problem.services.problem.DeleteProblemService deleteCoreProblemService;
    private TestCaseRepository testCaseRepository;
    private SubmissionRepository submissionRepository;
    private PostRepository postRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteById(Id id) throws NotFoundException {
        Problem problem = problemRepository.getById(id);
        if (problem == null)
            throw new NotFoundException("Unknown problem.");
        deleteCoreProblemService.deleteById(problem.getCoreProblemProblemId());
        problemRepository.deleteById(id);
        testCaseRepository.deleteByProblemId(id);
        submissionRepository.deleteAllByProblemId(id);
        postRepository.deleteAllByProblemId(id);
    }
}

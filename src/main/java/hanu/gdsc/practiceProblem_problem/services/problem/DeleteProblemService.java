package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.core_problem.repositories.submission.SubmissionRepository;
import hanu.gdsc.core_problem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.practiceProblem_problemDiscussion.repositories.post.PostRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
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
    private SubmissionRepository submissionRepository;
    private PostRepository postRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteById(Id id) {
        Problem problem = problemRepository.getById(id);
        if (problem == null)
            throw new NotFoundError("Unknown problem.");
        deleteCoreProblemService.deleteById(problem.getCoreProblemProblemId());
        problemRepository.deleteById(id);
        testCaseRepository.deleteByProblemId(id);
        submissionRepository.deleteAllByProblemId(id);
        postRepository.deleteAllByProblemId(id);
    }
}

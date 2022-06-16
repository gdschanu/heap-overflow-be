package hanu.gdsc.coreSubdomain.problemContext.services.problem;

import hanu.gdsc.coreSubdomain.problemContext.domains.Problem;
import hanu.gdsc.coreSubdomain.problemContext.domains.SubmissionCount;
import hanu.gdsc.coreSubdomain.problemContext.repositories.problem.ProblemRepository;
import hanu.gdsc.coreSubdomain.problemContext.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProblemServiceImpl implements CreateProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private SubmissionCountRepository submissionCountRepository;

    @Override
    public Id execute(Input input) {
        Problem problem = Problem.create(
                input.name,
                input.description,
                input.author,
                input.createMemoryLimitInputs,
                input.createTimeLimitInputs,
                input.allowedProgrammingLanguages,
                input.serviceToCreate
        );
        problemRepository.create(problem);

        SubmissionCount submissionCount = SubmissionCount.create(problem.getId(), input.serviceToCreate);
        submissionCountRepository.create(submissionCount);

        return problem.getId();
    }
}

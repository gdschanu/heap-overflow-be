package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_like.services.reactedObject.CreateReactedObjectService;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.core_problem.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private ProblemRepository problemRepository;
    private SubmissionCountRepository submissionCountRepository;


    @Override
    public Id execute(Input input) {
        Problem problem = Problem.create(
                input.name,
                input.description,
                input.author,
                input.memoryLimits,
                input.timeLimits,
                input.allowedProgrammingLanguages,
                input.serviceToCreate
        );
        problemRepository.create(problem);
        SubmissionCount submissionCount = SubmissionCount.create(problem.getId(), input.serviceToCreate);
        submissionCountRepository.create(submissionCount);

        return problem.getId();
    }
}

package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_like.services.reactedObject.CreateReactedObjectService;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.core_problem.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProblemServiceImpl implements CreateProblemService {
    @Autowired
    private CreateReactedObjectService createReactedObjectService;
    private final ProblemRepository problemRepository;
    private final SubmissionCountRepository submissionCountRepository;
    
    public CreateProblemServiceImpl(final ProblemRepository problemRepository,
                                    final SubmissionCountRepository submissionCountRepository) {
        this.problemRepository = problemRepository;
        this.submissionCountRepository = submissionCountRepository;                            
    }

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
        //Create parallely reactedObject with problemId
        createReactedObjectService.create(CreateReactedObjectService.Input.builder()
                .objectId(problem.getId())
                .serviceToCreate(input.serviceToCreate)
                .build());
        SubmissionCount submissionCount = SubmissionCount.create(problem.getId(), input.serviceToCreate);
        submissionCountRepository.create(submissionCount);

        return problem.getId();
    }
}

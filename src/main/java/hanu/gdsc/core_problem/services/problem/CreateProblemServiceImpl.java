package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.SubmissionCount;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.core_problem.repositories.submissionCount.SubmissionCountRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private ProblemRepository problemRepository;
    private SubmissionCountRepository submissionCountRepository;


    @Override
    public Id create(Input input) {
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

    @Override
    public List<Id> createMany(List<Input> inputs) {
        if (inputs.size() == 0)
            return new ArrayList<>();
        List<Problem> problems = new ArrayList<>();
        for (Input input : inputs)
            problems.add(Problem.create(
                    input.name,
                    input.description,
                    input.author,
                    input.memoryLimits,
                    input.timeLimits,
                    input.allowedProgrammingLanguages,
                    input.serviceToCreate
            ));
        List<SubmissionCount> submissionCounts = new ArrayList<>();
        for (Problem problem : problems)
            submissionCounts.add(SubmissionCount.create(problem.getId(), inputs.get(0).serviceToCreate));
        problemRepository.createMany(problems);
        submissionCountRepository.createMany(submissionCounts);
        return null;
    }
}

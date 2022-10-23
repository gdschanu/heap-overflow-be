package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.core_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.core_problem.repositories.SubmissionCountRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateProblemServiceImpl implements CreateProblemService {
    private ProblemRepository problemRepository;
    private SubmissionCountRepository submissionCountRepository;


    @Override
    public Id create(Input input) throws InvalidInputException {
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
    public List<Id> createMany(List<Input> inputs) throws InvalidInputException {
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
        return problems.stream()
                .map(problem -> problem.getId())
                .collect(Collectors.toList());
    }
}

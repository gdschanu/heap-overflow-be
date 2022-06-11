package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchProblemServiceImpl implements SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Output getById(Id id, String serviceToCreate) {
        Problem problem = problemRepository.getById(id, serviceToCreate);
        if (problem == null) {
            throw new BusinessLogicError("Could not found problem", "NOT_FOUND");
        }
        return toOutput(problem);
    }

    @Override
    public List<Output> getByIds(List<Id> ids, String serviceToCreate) {
        List<Problem> problems = problemRepository.getByIds(ids, serviceToCreate);
        return problems.stream().map(p -> toOutput(p)).collect(Collectors.toList());
    }


    private Output toOutput(Problem problem) {
        return Output.builder()
                .id(problem.getId())
                .name(problem.getName())
                .description(problem.getDescription())
                .author(problem.getAuthor())
                .memoryLimits(problem.getMemoryLimits().stream()
                        .map(m -> OutputMemoryLimit.builder()
                                .programmingLanguage(m.getProgrammingLanguage())
                                .memoryLimit(m.getMemoryLimit())
                                .build())
                        .collect(Collectors.toList()))
                .timeLimits(problem.getTimeLimits().stream()
                        .map(t -> OutputTimeLimit.builder()
                                .programmingLanguage(t.getProgrammingLanguage())
                                .timeLimit(t.getTimeLimit())
                                .build())
                        .collect(Collectors.toList()))
                .allowedProgrammingLanguages(problem.getAllowedProgrammingLanguages())
                .build();
    }
}

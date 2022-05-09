package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "PracticeProblem.SearchProblemServiceImpl")
public class SearchProblemServiceImpl implements SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = problemRepository.getById(practiceProblemId);
        if (practiceProblem == null) {
            throw new BusinessLogicError("Could not found problem", "NOT_FOUND");
        }
        return toOutput(practiceProblem);
    }

    @Override
    public List<Output> get(int page, int perPage) {
        List<Problem> practiceProblems = problemRepository.get(
                page,
                perPage
        );
        return practiceProblems.stream().map(p -> toOutput(p)).collect(Collectors.toList());
    }

    private Output toOutput(Problem practiceProblem) {
        return Output.builder()
                .id(practiceProblem.getId())
                .version(practiceProblem.getVersion())
                .coreProblemProblemId(practiceProblem.getCoreProblemProblemId())
                .categoryIds(practiceProblem.getCategoryIds())
                .difficulty(practiceProblem.getDifficulty())
                .build();
    }
}

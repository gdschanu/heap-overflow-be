package hanu.gdsc.practiceProblem_problem.services.problem;

import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.practiceProblem_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "PracticeProblem.SearchProblemServiceImpl")
public class SearchProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Output {
        public Id id;
        public long version;
        public Id coreProblemProblemId;
        private List<Id> categoryIds;
        public Difficulty difficulty;
    }


    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = problemRepository.getById(practiceProblemId);
        if (practiceProblem == null) {
            throw new BusinessLogicError("Could not found problem", "NOT_FOUND");
        }
        return toOutput(practiceProblem);
    }

    public List<Output> get(int page, int perPage) {
        List<Problem> practiceProblems = problemRepository.get(
                page,
                perPage
        );
        return practiceProblems.stream().map(p -> toOutput(p)).collect(Collectors.toList());
    }

    public long countProblem() {
        return problemRepository.countProblem();
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

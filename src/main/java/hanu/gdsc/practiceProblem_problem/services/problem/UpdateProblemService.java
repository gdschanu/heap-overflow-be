package hanu.gdsc.practiceProblem_problem.services.problem;

import java.util.List;

import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface UpdateProblemService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id problemId;
        public Id coreProblemId;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    public void update(Input input);
}
 
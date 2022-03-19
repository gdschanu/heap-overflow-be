package hanu.gdsc.practiceProblem.services.practiceProblem;

import java.util.List;

import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreatePracticeProblemService {
    @Builder
    public static class Input {
        public CreateProblemService.Input inputProblem;
        public List<String> categories;
        public Difficulty difficulty;
    }
    public Id create(Input input);
}

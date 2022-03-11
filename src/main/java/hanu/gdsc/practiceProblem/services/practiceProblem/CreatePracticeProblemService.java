package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreatePracticeProblemService {
    @Builder
    public static class Input {
        public CreateProblemService.Input inputProblem;
        public String nameCategory;
    }
    public Id create(Input input);
}

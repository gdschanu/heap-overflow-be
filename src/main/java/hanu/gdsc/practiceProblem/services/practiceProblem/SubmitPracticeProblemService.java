package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface SubmitPracticeProblemService {
    @Builder
    public static class Input {
        public Id practiceProblemId;
        public SubmitService.Input inputService;
    }
    
    public SubmitService.Output submit(Input input);
}

package hanu.gdsc.practiceProblemSubdomain.problemContext.services.core.problem.problem;

import hanu.gdsc.coreSubdomain.problemContext.domains.ProgrammingLanguage;
import hanu.gdsc.coreSubdomain.problemContext.services.submit.SubmitService;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface SubmitCoreProblemProblemService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    public SubmitService.Output submit(Input input);
}

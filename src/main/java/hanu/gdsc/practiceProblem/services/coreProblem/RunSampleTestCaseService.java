package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface RunSampleTestCaseService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    public hanu.gdsc.coreProblem.services.problem.RunSampleTestCaseService.Output runSampleTestCase(Input input);
}

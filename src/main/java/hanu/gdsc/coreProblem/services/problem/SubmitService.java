package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface SubmitService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;

        public Id problemId;
        public String serviceToCreate;

        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public FailedTestCaseDetail failedTestCaseDetail;
        public String compilationMessage;
        public String stdMessage;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FailedTestCaseDetail {
        public Integer failedAtLine;
        public String input;
        public String actualOutput;
        public String expectedOutput;
        public String description;
    }

    public Output submit(Input input);

}

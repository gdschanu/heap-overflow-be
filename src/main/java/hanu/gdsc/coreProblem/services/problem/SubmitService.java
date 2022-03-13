package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface SubmitService {
    @Builder
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @Builder
    public static class Output {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public TestCase failedTestCase;
    }

    public Output submit(Input input);

}

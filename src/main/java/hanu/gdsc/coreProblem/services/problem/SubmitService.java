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
        public String serviceName;
    }

    @Builder
    public static class Output {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public TestCase failedTestCase;
        public String actualOutput;
    }

    public Output submit(Input input);

}

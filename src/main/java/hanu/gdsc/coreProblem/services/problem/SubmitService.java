package hanu.gdsc.coreProblem.services.problem;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.*;
import lombok.Builder;

public interface SubmitService {
    @Builder
    public static class Input {
        public UUID coderId;
        public UUID problemId;
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

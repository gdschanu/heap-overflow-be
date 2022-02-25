package hanu.gdsc.problem.services.problem;

import hanu.gdsc.problem.domains.*;
import hanu.gdsc.problem.domains.Status;
import hanu.gdsc.share.domains.ID;
import lombok.Builder;

public interface SubmitService {
    @Builder
    public static class Input {
        public ID coderId;
        public ID problemId;
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

    public Output submitForCoder(Input input);

    public Output submitForSystem(Input input);

}

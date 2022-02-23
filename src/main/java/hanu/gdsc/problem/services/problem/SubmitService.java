package hanu.gdsc.problem.services.problem;

import hanu.gdsc.problem.domains.*;
import hanu.gdsc.problem.domains.submission.Status;
import lombok.Builder;

public interface SubmitService {
    @Builder
    public static class SubmitInput {
        public ID coderId;
        public ID problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @Builder
    public static class InContestSubmitOutput {
        public Millisecond runTime;
        public KB memory;
        public Status status;
    }

    public InContestSubmitOutput inContestSubmit(SubmitInput input);

    @Builder
    public static class OutContestSubmitOutput {
        public Millisecond runTime;
        public KB memory;
        public Status status;
        public TestCase failedTestCase;
    }

    public OutContestSubmitOutput outContestSubmit(SubmitInput input);

}

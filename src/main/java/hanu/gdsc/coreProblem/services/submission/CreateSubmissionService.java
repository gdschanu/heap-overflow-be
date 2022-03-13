package hanu.gdsc.coreProblem.services.submission;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.domains.TestCase;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateSubmissionService {
    @Builder
    public static class Input {
        public Id problemId;
        public ProgrammingLanguage programmingLanguage;
        public Millisecond runTime;
        public KB memory;
        public DateTime submittedAt;
        public String code;
        public Status status;
        public TestCase failedTestCase;
    }

    public Id create(Input input);
}

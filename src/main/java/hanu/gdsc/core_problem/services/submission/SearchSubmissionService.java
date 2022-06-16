package hanu.gdsc.core_problem.services.submission;

import hanu.gdsc.core_problem.domains.KB;
import hanu.gdsc.core_problem.domains.Millisecond;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.Status;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface SearchSubmissionService {
    @Builder
    public static class FailedTestCaseDetailOutput {
        public Integer failedAtLine;
        public String input;
        public String actualOutput;
        public String expectedOutput;
        public String description;
    
    }

    @Builder
    public static class Output {
        public Id problemId;
        public ProgrammingLanguage programmingLanguage;
        public Millisecond runTime;
        public KB memory;
        public DateTime submittedAt;
        public String code;
        public Status status;
        public FailedTestCaseDetailOutput failedTestCaseDetail;
        public Id coderId;
        public String message;
    }

    public List<Output> get(int page,
                                int perPage,
                                Id problemId,
                                Id coderId,
                                String serviceToCreate);

    public Output getById(Id id, String serviceToCreate);
}


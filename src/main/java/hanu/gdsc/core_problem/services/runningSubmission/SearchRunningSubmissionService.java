package hanu.gdsc.core_problem.services.runningSubmission;

import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public interface SearchRunningSubmissionService {

    @Builder
    @Getter
    public static class Output {
        private Id coderId;
        private Id problemId;
        private String code;
        private ProgrammingLanguage programmingLanguage;
        private DateTime submittedAt;

        private int judgingTestCase;
        private int totalTestCases;
    }

    public Output getByIdAndCoderId(Id id, Id coderId, String serviceToCreate);

    public List<Output> getByCoderId(int page, int perPage, Id coderId, String serviceToCreate);

    public Output getById(Id id, String serviceToCreate);
}

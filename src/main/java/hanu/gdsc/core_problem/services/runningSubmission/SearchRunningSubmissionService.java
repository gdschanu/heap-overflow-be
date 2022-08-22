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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
        public DateTime submittedAt;

        public int judgingTestCase;
        public int totalTestCases;
    }

    public Output getByIdAndCoderId(Id id, Id coderId, String serviceToCreate);

    public List<Output> getByCoderId(int page, int perPage, Id coderId, String serviceToCreate);

    public Output getById(Id id, String serviceToCreate);
}

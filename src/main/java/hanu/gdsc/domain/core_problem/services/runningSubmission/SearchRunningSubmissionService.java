package hanu.gdsc.domain.core_problem.services.runningSubmission;

import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public interface SearchRunningSubmissionService {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        public Id id;
        public Id coderId;
        public Id problemId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
        public DateTime submittedAt;

        public int judgingTestCase;
        public int totalTestCases;
    }

    public List<Output>     getByProblemIdAndCoderId(Id problemId,
                                                 Id coderId,
                                                 int page,
                                                 int perPage,
                                                 String serviceToCreate);

    public Output getById(Id id, String serviceToCreate);
}

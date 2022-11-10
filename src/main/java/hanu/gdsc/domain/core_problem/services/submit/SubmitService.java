package hanu.gdsc.domain.core_problem.services.submit;

import hanu.gdsc.domain.core_problem.exceptions.NoTestCasesWereDefined;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface SubmitService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String serviceToCreate;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id submissionId;
    }

    public Output submit(Input input) throws InvalidInputException, NoTestCasesWereDefined;
}

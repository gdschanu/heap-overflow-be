package hanu.gdsc.core_problem.services.submit;

import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface SubmitService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String serviceToCreate;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @AllArgsConstructor
    @Getter
    public static class Output {
        public Id submissionId;
    }

    public Output submit(Input input);
}

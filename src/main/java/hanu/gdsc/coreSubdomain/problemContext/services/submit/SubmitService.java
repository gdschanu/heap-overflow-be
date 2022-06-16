package hanu.gdsc.coreSubdomain.problemContext.services.submit;

import hanu.gdsc.coreSubdomain.problemContext.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface SubmitService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id problemId;
        public String serviceToCreate;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id submissionId;
    }

    public Output submit(Input input);
}

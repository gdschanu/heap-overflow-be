package hanu.gdsc.core_problem.services.testCase;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public interface CreateTestCaseService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
        public String serviceToCreate;
    }

    public void create(Input input);
}

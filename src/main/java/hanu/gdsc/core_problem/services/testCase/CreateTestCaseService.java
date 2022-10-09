package hanu.gdsc.core_problem.services.testCase;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface CreateTestCaseService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
        public String serviceToCreate;
    }

    public void create(Input input) throws InvalidInputException;
}

package hanu.gdsc.domain.core_problem.services.testCase;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
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

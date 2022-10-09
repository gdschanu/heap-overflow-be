package hanu.gdsc.core_problem.services.testCase;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
import lombok.*;

public interface UpdateTestCaseService {
    @Builder
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

    public void update(Input input) throws NotFoundException;
}

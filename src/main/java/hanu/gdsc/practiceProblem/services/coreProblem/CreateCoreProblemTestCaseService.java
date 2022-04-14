package hanu.gdsc.practiceProblem.services.coreProblem;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateCoreProblemTestCaseService {
    @Builder
    public static class Input {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
        public String serviceToCreate;
    }

    public void create(List<Input> input);
}

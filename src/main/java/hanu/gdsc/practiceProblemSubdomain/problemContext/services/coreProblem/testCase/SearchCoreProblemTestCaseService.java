package hanu.gdsc.practiceProblemSubdomain.problemContext.services.coreProblem.testCase;

import java.util.List;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface SearchCoreProblemTestCaseService {
    @Builder
    public static class Output {
        public Id problemId;
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }
    public List<Output> get(Id problemId);
    public List<Output> getSampleTestCases(Id problemId);
}

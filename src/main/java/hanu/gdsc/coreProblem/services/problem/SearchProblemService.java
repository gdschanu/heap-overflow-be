package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


public interface SearchProblemService {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputTestCase {
        private String input;
        private String expectedOutput;
        private int ordinal;
        private boolean isSample;
        private String description;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputMemoryLimit {
        private ProgrammingLanguage programmingLanguage;
        private KB memoryLimit;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputTimeLimit {
        private ProgrammingLanguage programmingLanguage;
        private Millisecond timeLimit;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public String name;
        public String description;
        public Id author;
        public List<OutputTestCase> testCases;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public String serviceToCreate;
    }

    public Output getById(Id id);
}

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
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputMemoryLimit {
        public ProgrammingLanguage programmingLanguage;
        public KB memoryLimit;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OutputTimeLimit {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public Id id;
        public String name;
        public String description;
        public Id author;
        public List<OutputTestCase> testCases;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public Output getById(Id id, String serviceToCreate);

    public List<Output> getByIds(List<Id> ids, String serviceToCreate);
}

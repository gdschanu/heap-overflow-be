package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.practiceProblem.domains.Difficulty;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public interface SearchPracticeProblemService {

    @Builder
    public static class OutputTestCase {
        public String input;
        public String expectedOutput;
        public int ordinal;
        public boolean isSample;
        public String description;
    }

    @Builder
    public static class OutputMemoryLimit {
        public ProgrammingLanguage programmingLanguage;
        public KB memoryLimit;
    }

    @Builder
    public static class OutputTimeLimit {
        public ProgrammingLanguage programmingLanguage;
        public Millisecond timeLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OutputCoreProblem {
        public Id id;
        public long version;
        public String name;
        public String description;
        public Id author;
        public int ACsCount;
        public int submissionsCount;
        public List<OutputTestCase> testCases;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OutputPracticeProblem {
        public Id id;
        public long version;
        public Id coreProlemId;
        public long likeCount;
        public long dislikeCount;
        private List<Id> categoryIds;
        public Difficulty difficulty;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Output {
        public OutputPracticeProblem practiceProblem;
        public OutputCoreProblem coreProblem;
    }

    public Output getById(Id practiceProblemId);

    public List<Output> get(int page, int perPage);
}

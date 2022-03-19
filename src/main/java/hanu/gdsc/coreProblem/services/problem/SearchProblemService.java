package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


public interface SearchProblemService {

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
        public long memoryLimit;
    }

    @Builder
    public static class OutputTimeLimit {
        public ProgrammingLanguage programmingLanguage;
        public long timeLimit;
    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OutputCoreProblem {
        public String id;
        public long version;
        public String name;
        public String description;
        public String author;
        public String difficulty;
        public List<OutputTestCase> testCases;
        public List<OutputMemoryLimit> memoryLimits;
        public List<OutputTimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public Problem getById(Id id);
    
    public List<Problem> search (Pageable pageable);

    public long count();
}

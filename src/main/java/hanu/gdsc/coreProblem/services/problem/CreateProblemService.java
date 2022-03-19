package hanu.gdsc.coreProblem.services.problem;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface CreateProblemService {
    @Builder
    public static class Input {
        public String name;
        public String description;
        public Id author;
        public Difficulty difficulty;
        List<TestCase.CreateInput> createTestCaseInputs;
        List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public String serviceToCreate;
    }

    public Id execute(Input input);
}

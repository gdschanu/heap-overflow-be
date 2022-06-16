package hanu.gdsc.practiceProblem_problem.services.problem;

import java.util.List;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.practiceProblem_problem.domains.Difficulty;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateProblemService {
    @Builder
    public static class Input {
        public CreateCoreProblemInput createCoreProblemInput;
        public List<Id> categoryIds;
        public Difficulty difficulty;
    }

    @Builder
    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    public Id create(Input input);
}

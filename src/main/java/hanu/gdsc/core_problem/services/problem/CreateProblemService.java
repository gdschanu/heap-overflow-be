package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface CreateProblemService {
    @Builder
    public static class Input {
        public String name;
        public String description;
        public Id author;
        List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public String serviceToCreate;
    }

    public Id execute(Input input);
}

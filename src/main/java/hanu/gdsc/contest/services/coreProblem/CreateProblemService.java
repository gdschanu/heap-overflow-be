package hanu.gdsc.contest.services.coreProblem;

import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface CreateProblemService {
    @Builder
    public static class Input {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    public Id execute(Input input);
}

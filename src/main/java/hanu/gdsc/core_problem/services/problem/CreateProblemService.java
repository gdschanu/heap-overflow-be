package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.MemoryLimit;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.domains.TimeLimit;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface CreateProblemService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        public String name;
        public String description;
        public Id author;
        List<MemoryLimit.CreateInput> memoryLimits;
        List<TimeLimit.CreateInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public String serviceToCreate;
    }

    public Id create(Input input);

    public List<Id> createMany(List<Input> inputs);
}

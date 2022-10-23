package hanu.gdsc.domain.core_problem.services.problem;

import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public interface CreateProblemService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String name;
        public String description;
        public Id author;
        List<MemoryLimit.CreateInputML> memoryLimits;
        List<TimeLimit.CreateInputTL> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public String serviceToCreate;
    }

    public Id create(Input input) throws InvalidInputException;

    public List<Id> createMany(List<Input> inputs) throws InvalidInputException;
}

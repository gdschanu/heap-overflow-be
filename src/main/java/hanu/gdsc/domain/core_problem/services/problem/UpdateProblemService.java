package hanu.gdsc.domain.core_problem.services.problem;

import java.util.List;

import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.*;

public interface UpdateProblemService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input{
        public Id id;
        public String serviceToCreate;
        public String name;
        public String description;
        public List<MemoryLimit.CreateInputML> memoryLimits;
        public List<TimeLimit.CreateInputTL> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(Input input) throws NotFoundException, InvalidInputException;
}

package hanu.gdsc.core_problem.services.problem;

import java.util.List;

import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface UpdateProblemService {
    @AllArgsConstructor
    @Getter
    public static class Input{
        public Id id;
        public String serviceToCreate;
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> memoryLimits;
        public List<TimeLimit.CreateInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(Input input);
}

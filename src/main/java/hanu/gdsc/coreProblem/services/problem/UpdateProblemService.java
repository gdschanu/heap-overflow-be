package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import hanu.gdsc.coreProblem.domains.MemoryLimit;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.coreProblem.domains.TimeLimit;
import hanu.gdsc.share.domains.Id;

public interface UpdateProblemService {
    public static class Input{
        public Id id;
        public String serviceToCreate;
        public String name;
        public String description;
        public List<MemoryLimit> memoryLimits;
        public List<TimeLimit> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public Id update(Input input);
}

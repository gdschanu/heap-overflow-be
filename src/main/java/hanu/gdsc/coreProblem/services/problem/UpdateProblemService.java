package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import hanu.gdsc.coreProblem.domains.KB;
import hanu.gdsc.coreProblem.domains.Millisecond;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface UpdateProblemService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input{
        public Id id;
        public String serviceToCreate;
        public String name;
        public String description;
        public List<UpdateMemoryLimitInput> memoryLimits;
        public List<UpdateTimeLimitInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateMemoryLimitInput {
        public KB memoryLimit;
        public ProgrammingLanguage programmingLanguage;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateTimeLimitInput {
        public Millisecond timeLimit;
        public ProgrammingLanguage programmingLanguage;
    }

    public void update(Input input);
}

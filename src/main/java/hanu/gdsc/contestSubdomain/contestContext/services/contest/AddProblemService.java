package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.coreSubdomain.problemContext.domains.MemoryLimit;
import hanu.gdsc.coreSubdomain.problemContext.domains.ProgrammingLanguage;
import hanu.gdsc.coreSubdomain.problemContext.domains.TimeLimit;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface AddProblemService {

    @Builder
    public static class CreateCoreProblemInput {
        public String name;
        public String description;
        public List<MemoryLimit.CreateInput> createMemoryLimitInputs;
        public List<TimeLimit.CreateInput> createTimeLimitInputs;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
        public Id author;
    }

    @Builder
    public static class Input {
        public Id contestId;
        public int ordinal;
        public int score;
        public CreateCoreProblemInput createCoreProblemInput;
    }

    public void execute(Input input);
}

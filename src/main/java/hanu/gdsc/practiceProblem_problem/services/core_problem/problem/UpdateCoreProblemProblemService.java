package hanu.gdsc.practiceProblem_problem.services.core_problem.problem;

import java.util.List;

import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.services.problem.UpdateProblemService;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface UpdateCoreProblemProblemService {
    @Builder
    public static class Input {
        public Id id;
        public String name;
        public String description;
        public List<UpdateProblemService.UpdateMemoryLimitInput> memoryLimits;
        public List<UpdateProblemService.UpdateTimeLimitInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(Input input);
}

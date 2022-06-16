package hanu.gdsc.practiceProblem_problem.services.core_problem_problem;

import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.core_problem.services.problem.UpdateProblemService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateCoreProblemProblemService {
    @Autowired
    private UpdateProblemService updateProblemService;

    @Builder
    public static class Input {
        public Id id;
        public String name;
        public String description;
        public List<UpdateProblemService.UpdateMemoryLimitInput> memoryLimits;
        public List<UpdateProblemService.UpdateTimeLimitInput> timeLimits;
        public List<ProgrammingLanguage> allowedProgrammingLanguages;
    }

    public void update(Input input) {
        updateProblemService.update(UpdateProblemService.Input.builder()
                .id(input.id)
                .serviceToCreate(ServiceName.serviceName)
                .name(input.name)
                .description(input.description)
                .memoryLimits(input.memoryLimits)
                .timeLimits(input.timeLimits)
                .allowedProgrammingLanguages(input.allowedProgrammingLanguages)
                .build());
    }

}

package hanu.gdsc.practiceProblem_problem.services.core_problem.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.core_problem.services.problem.UpdateProblemService;
import hanu.gdsc.practiceProblem_problem.config.ServiceName;

@Service
public class UpdateCoreProblemProblemServiceImpl implements UpdateCoreProblemProblemService{
    @Autowired
    private UpdateProblemService updateProblemService;

    @Override
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

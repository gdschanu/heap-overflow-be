package hanu.gdsc.practiceProblem.services.coreProblem.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.UpdateProblemService;
import hanu.gdsc.practiceProblem.config.ServiceName;

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

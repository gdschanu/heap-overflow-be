package hanu.gdsc.practiceProblem.services.problem;

import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.ProblemRepository;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProblemServiceImpl implements CreateProblemService {
    @Autowired
    private hanu.gdsc.coreProblem.services.problem.CreateProblemService createProblemService;
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Id create(Input input) {
        Id coreProblemId = createProblemService.execute(hanu.gdsc.coreProblem.services.problem.CreateProblemService.Input.builder()
                .name(input.createCoreProblemInput.name)
                .description(input.createCoreProblemInput.description)
                .author(input.createCoreProblemInput.author)
                .createTestCaseInputs(input.createCoreProblemInput.createTestCaseInputs)
                .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                .serviceToCreate(ServiceName.serviceName)
                .build());
        Problem practiceProblem = Problem.create(coreProblemId, input.categoryIds, input.difficulty);
        problemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
    
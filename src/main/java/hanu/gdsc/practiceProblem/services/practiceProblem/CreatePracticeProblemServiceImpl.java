package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import hanu.gdsc.practiceProblem.config.ServiceName;
import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.practiceProblem.services.category.SearchCategoryService;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatePracticeProblemServiceImpl implements CreatePracticeProblemService {
    @Autowired
    private CreateProblemService createProblemService;
    @Autowired
    private SearchCategoryService searchCategoryService;
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;

    @Override
    public Id create(Input input) {
        Id coreProblemId = createProblemService.execute(CreateProblemService.Input.builder()
                .name(input.createCoreProblemInput.name)
                .description(input.createCoreProblemInput.description)
                .author(input.createCoreProblemInput.author)
                .createTestCaseInputs(input.createCoreProblemInput.createTestCaseInputs)
                .createMemoryLimitInputs(input.createCoreProblemInput.createMemoryLimitInputs)
                .createTimeLimitInputs(input.createCoreProblemInput.createTimeLimitInputs)
                .allowedProgrammingLanguages(input.createCoreProblemInput.allowedProgrammingLanguages)
                .serviceToCreate(ServiceName.serviceName)
                .build());
        List<Id> categoryIds = new ArrayList<>();
        for (String categoryName : input.categories) {
            Category category = searchCategoryService.getByName(categoryName);
            categoryIds.add(category.getId());
        }
        Problem practiceProblem = Problem.create(coreProblemId, categoryIds, input.difficulty);
        practiceProblemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
    
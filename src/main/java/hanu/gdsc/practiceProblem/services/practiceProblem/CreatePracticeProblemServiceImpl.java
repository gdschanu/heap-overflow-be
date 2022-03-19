package hanu.gdsc.practiceProblem.services.practiceProblem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.CreateProblemService;
import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.practiceProblem.services.category.SearchCategoryService;
import hanu.gdsc.share.domains.Id;

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
        input.inputProblem.serviceToCreate = "CreatePracticeProblemService";
        Id coreProblemId = createProblemService.execute(input.inputProblem);
        List<Id> categoryIds = new ArrayList<>();
        for(String categoryName : input.categorys ) {
            Category category = searchCategoryService.getByName(categoryName);
            categoryIds.add(category.getId());
        }
        Problem practiceProblem = Problem.create(coreProblemId, categoryIds, input.difficulty);
        practiceProblemRepository.create(practiceProblem);
        return practiceProblem.getId();
    }
}
    
package hanu.gdsc.practiceProblem_problem.services.category;

import hanu.gdsc.practiceProblem_problem.domains.Category;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCategoryService {
    public Category getById(Id id);

    public List<Category> get();
}

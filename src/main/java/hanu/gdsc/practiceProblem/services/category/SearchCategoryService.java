package hanu.gdsc.practiceProblem.services.category;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCategoryService {
    public Category getById(Id id);

    public List<Category> get();
}

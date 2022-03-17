package hanu.gdsc.practiceProblem.services.category;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.share.domains.Id;

public interface SearchCategoryService {
    public Category getById(Id id);
    public Category getByName(String name);
}

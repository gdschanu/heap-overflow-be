package hanu.gdsc.practiceProblemSubdomain.problemContext.services.category;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Category;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface SearchCategoryService {
    public Category getById(Id id);

    public List<Category> get();
}

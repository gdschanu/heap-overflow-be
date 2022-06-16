package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.category;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Category;
import hanu.gdsc.share.domains.Id;

public interface CategoryRepository {
    public void create(Category category);
    public Category getById(Id id);
    public Category getByName(String name);
}

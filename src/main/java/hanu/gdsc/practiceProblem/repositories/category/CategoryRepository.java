package hanu.gdsc.practiceProblem.repositories.category;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.share.domains.Id;

public interface CategoryRepository {
    public void create(Category category);
    public Category getById(Id id);
    public Category getByName(String name);
}

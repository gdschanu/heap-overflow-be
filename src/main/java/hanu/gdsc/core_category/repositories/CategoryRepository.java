package hanu.gdsc.core_category.repositories;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.domains.Id;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository{
    public Category save(Category category);

    public Category findById(Id id);

    public void deleteById(Id id, String serviceToCreate);


    public void deleteMany(List<Id> ids);
}

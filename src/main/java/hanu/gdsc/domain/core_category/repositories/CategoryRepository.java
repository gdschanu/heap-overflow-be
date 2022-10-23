package hanu.gdsc.domain.core_category.repositories;

import hanu.gdsc.domain.core_category.models.Category;
import hanu.gdsc.domain.share.models.Id;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository{
    public Category save(Category category);

    public Category findById(Id id);

    public void deleteById(Id id, String serviceToCreate);


    public void deleteMany(List<Id> ids);
}

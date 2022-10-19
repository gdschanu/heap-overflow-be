package hanu.gdsc.core_category.services.category;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface DeleteCategoryService {

    public void deleteById(Id id, String serviceToCreate);

    public void deleteMany(List<Id> ids);

}

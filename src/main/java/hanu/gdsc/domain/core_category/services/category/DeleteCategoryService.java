package hanu.gdsc.domain.core_category.services.category;

import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface DeleteCategoryService {

    public void deleteById(Id id, String serviceToCreate);

    public void deleteMany(List<Id> ids);

}

package hanu.gdsc.core_category.services.category;

import hanu.gdsc.share.domains.Id;

public interface DeleteCategoryService {

    public void deleteById(Id id);

    public void deleteMany(Id[] ids);

}

package hanu.gdsc.core_category.services.category;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CreateCategoryService {

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String name;
        public String serviceToCreate;
    }

    public void create(String name);


}

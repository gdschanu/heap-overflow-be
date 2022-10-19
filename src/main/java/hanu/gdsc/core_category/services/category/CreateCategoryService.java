package hanu.gdsc.core_category.services.category;

import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CreateCategoryService {

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String name;
        public String serviceToCreate;
    }

    public Id create(Input input) throws InvalidInputException;

}

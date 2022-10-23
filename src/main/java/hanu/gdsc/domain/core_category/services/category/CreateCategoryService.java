package hanu.gdsc.domain.core_category.services.category;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
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

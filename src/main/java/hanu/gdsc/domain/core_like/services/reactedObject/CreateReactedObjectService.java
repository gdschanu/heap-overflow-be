package hanu.gdsc.domain.core_like.services.reactedObject;

import hanu.gdsc.domain.share.models.Id;
import lombok.*;

public interface CreateReactedObjectService {
    
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String serviceToCreate;
    }

    public Id create(Input input);
}

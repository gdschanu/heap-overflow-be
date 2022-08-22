package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface CreateReactedObjectService {
    
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String serviceToCreate;
    }

    public Id create(Input input);
}

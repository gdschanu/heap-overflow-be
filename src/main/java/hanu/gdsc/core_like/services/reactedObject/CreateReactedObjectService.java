package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface CreateReactedObjectService {
    
    @AllArgsConstructor
    @Getter
    public static class Input {
        private String serviceToCreate;
    }

    public Id create(Input input);
}

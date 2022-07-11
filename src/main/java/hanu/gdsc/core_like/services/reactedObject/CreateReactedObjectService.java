package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface CreateReactedObjectService {
    
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id objectId;
        public String serviceToCreate;
    }

    public void create(Input input);
}

package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.core_like.domains.Action;
import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface ReactService {
    
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id reactedObjectId;
        public Id coderId;
        public Action action;
    }

    public boolean react(Input input);
}

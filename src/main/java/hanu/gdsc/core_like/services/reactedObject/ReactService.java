package hanu.gdsc.core_like.services.reactedObject;

import hanu.gdsc.core_like.domains.Action;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface ReactService {
    
    @AllArgsConstructor
    @Getter
    public static class Input {
        public Id reactedObjectId;
        public Id coderId;
        public Action action;
        public String serviceToCreate;
    }

    public void react(Input input);
}

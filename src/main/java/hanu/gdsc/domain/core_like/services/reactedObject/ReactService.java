package hanu.gdsc.domain.core_like.services.reactedObject;

import hanu.gdsc.domain.core_like.models.Action;
import hanu.gdsc.domain.core_like.exceptions.InvalidActionException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface ReactService {
    
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id reactedObjectId;
        public Id coderId;
        public Action action;
        public String serviceToCreate;
    }

    public void react(Input input) throws NotFoundException, InvalidActionException;
}
